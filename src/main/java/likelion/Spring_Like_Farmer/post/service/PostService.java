package likelion.Spring_Like_Farmer.post.service;

import com.fasterxml.jackson.databind.ser.std.FileSerializer;
import likelion.Spring_Like_Farmer.file.FileService;
import likelion.Spring_Like_Farmer.item.domain.Item;
import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.post.dto.PostDto;
import likelion.Spring_Like_Farmer.post.repository.PostRepository;
import likelion.Spring_Like_Farmer.record.dto.RecordDto;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.user.repository.UserRepository;
import likelion.Spring_Like_Farmer.validation.CustomException;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    public Object savePost(UserPrincipal postPrincipal, PostDto.SavePost savePost) {
        User user = userRepository.findByUserId(postPrincipal.getUserId()).get();

        Post post = Post.builder()
                .savePost(savePost)
                .user(user)
                .build();
        postRepository.save(post);
        return new PostDto.PostResponse(ExceptionCode.POST_SAVE_OK);
    }

    public Object savePost(UserPrincipal postPrincipal, PostDto.SavePost savePost, MultipartFile file) {
        User user = userRepository.findByUserId(postPrincipal.getUserId()).get();

        Post post = Post.builder()
                .savePost(savePost)
                .user(user)
                .build();

        /*if (file != null) {
            String image = fileService.saveFile(post.getPostId(), file, "post");
            post.setImage(image);
        } else {
            post.setImage(null);
        }*/

        postRepository.save(post);

        return new PostDto.PostResponse(ExceptionCode.POST_SAVE_OK);
    }

    public Object updatePost(UserPrincipal postPrincipal, Long postId, PostDto.SavePost savePost) {
        Optional<Post> findPost = postRepository.findByPostId(postId);
        if (findPost.isEmpty()) {
            return new RecordDto.RecordResponse(ExceptionCode.POST_NOT_FOUND);
        }
        Post post = findPost.get();

        post.updatePost(savePost);

        postRepository.save(post);
        return new PostDto.PostResponse(ExceptionCode.POST_UPDATE_OK);
    }

    public Object updatePost(UserPrincipal postPrincipal, Long postId, PostDto.SavePost savePost, MultipartFile file) {
        Optional<Post> findPost = postRepository.findByPostId(postId);
        if (findPost.isEmpty()) {
            return new RecordDto.RecordResponse(ExceptionCode.POST_NOT_FOUND);
        }
        Post post = findPost.get();

        post.updatePost(savePost);

        /*if (file != null) {
            String image = fileService.saveFile(post.getPostId(), file, "post");
            post.setImage(image);
        } else {
            post.setImage(null);
        }*/
        postRepository.save(post);
        return new PostDto.PostResponse(ExceptionCode.POST_UPDATE_OK);
    }


    public Object deletePost(UserPrincipal postPrincipal, Long postId) {
        Optional<Post> findPost = postRepository.findByPostId(postId);
        if (findPost.isEmpty()) {
            return new RecordDto.RecordResponse(ExceptionCode.POST_NOT_FOUND);
        }
        Post post = findPost.get();
        postRepository.delete(post);
        return new PostDto.PostResponse(ExceptionCode.POST_DELETE_OK);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

}
