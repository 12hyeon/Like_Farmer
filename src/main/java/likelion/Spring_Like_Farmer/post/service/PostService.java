package likelion.Spring_Like_Farmer.post.service;

import likelion.Spring_Like_Farmer.file.FileService;
import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.post.dto.PostDto;
import likelion.Spring_Like_Farmer.post.repository.PostRepository;
import likelion.Spring_Like_Farmer.record.dto.RecordDto;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.user.repository.UserRepository;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

//    public Object savePost(UserPrincipal postPrincipal, MultipartFile file) { // 게시글 올리기 : 이미지만, 나머지는 null
//        User user = userRepository.findByUserId(postPrincipal.getUserId()).get();
//        PostDto.SavePost savePost = new PostDto.SavePost("", "", "");
//
//        Post post = Post.builder()
//                .savePost(savePost)
//                .user(user)
//                .build();
//
//        if (file != null) {
//            String image = fileService.saveFile(post.getPostId(), file, "post");
//            post.setImage(image);
//        } else {
//            post.setImage(null);
//        }
//        postRepository.save(post);
//        return new PostDto.PostResponse(ExceptionCode.POST_SAVE_OK);
//    }

    public Object savePost(UserPrincipal userPrincipal, PostDto.SavePost savePost, MultipartFile file) {
        User user = userRepository.findByUserId(userPrincipal.getUserId()).get();

        // 내용이나 파일 중 하나라도 있는 경우만 게시글을 저장함
        if (!savePost.getLocation().isEmpty() || !savePost.getDescription().isEmpty() || (file != null && !file.isEmpty())) {
            Post post = Post.builder()
                    .savePost(savePost)
                    .user(user)
                    .build();

            if (file != null && !file.isEmpty()) {
                String image = fileService.saveFile(post.getPostId(), file, "post");
                post.setImage(image);
            } else {
                post.setImage(null);
            }
            postRepository.save(post);

            // 게시물을 저장한 사용자의 tier를 증가시키는 로직 추가
            if (user.getTier() == 2) {
                user.setTier(3);
                userRepository.save(user);
            }

            return new PostDto.PostResponse(ExceptionCode.POST_SAVE_OK);
        } else {
            return new PostDto.PostResponse(ExceptionCode.FILE_NOT_FOUND);
        }
    }


//    public Object updatePost(UserPrincipal postPrincipal, Long postId, PostDto.SavePost savePost) { // 게시글 수정 : 내용 수정
//        Optional<Post> findPost = postRepository.findByPostId(postId);
//        if (findPost.isEmpty()) {
//            return new RecordDto.RecordResponse(ExceptionCode.POST_NOT_FOUND);
//        }
//        Post post = findPost.get();
//
//        post.updatePost(savePost);
//
//        postRepository.save(post);
//        return new PostDto.PostResponse(ExceptionCode.POST_UPDATE_OK);
//    }

    public Object updatePost(UserPrincipal userPrincipal, Long postId, PostDto.SavePost savePost, MultipartFile file) {
        User user = userRepository.findByUserId(userPrincipal.getUserId()).get();

        Optional<Post> findPost = postRepository.findByPostId(postId);
        if (findPost.isEmpty()) {
            return new RecordDto.RecordResponse(ExceptionCode.POST_NOT_FOUND);
        }
        Post post = findPost.get();

        if (!post.getUser().equals(user)) {
            return new RecordDto.RecordResponse(ExceptionCode.INVALID_USER);
        }

        if (!savePost.getLocation().isEmpty() || !savePost.getDescription().isEmpty() || (file != null && !file.isEmpty())) {
            if (file != null && !file.isEmpty()) {
                String image = fileService.saveFile(post.getPostId(), file, "post");
                post.setImage(image);
            }

            post.updatePost(savePost);
            postRepository.save(post);
            return new PostDto.PostResponse(ExceptionCode.POST_UPDATE_OK);
        } else {
            return new PostDto.PostResponse(ExceptionCode.FILE_NOT_FOUND);
        }
    }


    public Object deletePost(UserPrincipal userPrincipal, Long postId) {
        User user = userRepository.findByUserId(userPrincipal.getUserId()).get();

        Optional<Post> findPost = postRepository.findByPostId(postId);
        if (findPost.isEmpty()) {
            return new RecordDto.RecordResponse(ExceptionCode.POST_NOT_FOUND);
        }
        Post post = findPost.get();

        if (!post.getUser().equals(user)) {
            return new RecordDto.RecordResponse(ExceptionCode.INVALID_USER);
        }

        // 이미지 파일 삭제 - 추가된 부분
        if (post.getImage() != null && !post.getImage().isEmpty()) {
            fileService.deleteFile(post.getPostId(), post.getImage(), "post");
        }

        postRepository.delete(post);
        return new PostDto.PostResponse(ExceptionCode.POST_DELETE_OK);
    }

    public Object findAllPosts() {
        List<Post> all = postRepository.findAllByOrderByCreatedAtDesc();
        return new PostDto.PostListResponse(ExceptionCode.POST_GET_OK, all);
    }

    public Object findPost(Long postId) {

        Optional<Post> findPost = postRepository.findByPostId(postId);
        if (findPost.isEmpty()) {
            return new RecordDto.RecordResponse(ExceptionCode.POST_NOT_FOUND);
        }
        Post post = findPost.get();
        return new PostDto.PostResponse(ExceptionCode.POST_GET_OK, post);
    }

}
