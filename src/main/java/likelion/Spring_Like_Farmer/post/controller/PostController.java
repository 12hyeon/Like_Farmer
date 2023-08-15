package likelion.Spring_Like_Farmer.post.controller;

import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.post.dto.PostDto;
import likelion.Spring_Like_Farmer.post.service.PostService;
import likelion.Spring_Like_Farmer.security.CurrentUser;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

//    // 게시글 올리기 : 글만, 이미지값: null
//    @PostMapping("/save")
//    public ResponseEntity<Object> savePost(@CurrentUser UserPrincipal postPrincipal,
//                                           @RequestBody PostDto.SavePost savePost) {
//        return new ResponseEntity<>(postService.savePost(postPrincipal, savePost, null), HttpStatus.OK);
//    }

     // 게시글 올리기 : 이미지만, 나머지는 null
    @PostMapping("/save")
    public ResponseEntity<Object> savePost(@CurrentUser UserPrincipal postPrincipal,
                                           @RequestPart(value= "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(postService.savePost(postPrincipal, file), HttpStatus.OK);
    }

    // 게시물 수정 : 파일 수정
    @PatchMapping("/{postId}/file")
    public ResponseEntity<Object> updatePostWithFile(@CurrentUser UserPrincipal postPrincipal,
                                                     @PathVariable Long postId,
                                                     @RequestPart(value = "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(postService.updatePost(postPrincipal, postId, file), HttpStatus.OK);
    }

    // 게시글 수정 : 내용 수정
    @PatchMapping("/{postId}/update")
    public ResponseEntity<Object> updatePost(@CurrentUser UserPrincipal postPrincipal,
                                             @PathVariable Long postId,
                                             @RequestBody PostDto.SavePost savePost) {
        return new ResponseEntity<>(postService.updatePost(postPrincipal, postId, savePost), HttpStatus.OK);
    }

    // 게시글 삭제하기
    @DeleteMapping("/{postId}")
    public ResponseEntity<Object> deletePost(@CurrentUser UserPrincipal postPrincipal,
                                             @PathVariable Long postId) {
        return new ResponseEntity<>(postService.deletePost(postPrincipal, postId), HttpStatus.OK);
    }

    // 게시물 전체 불러오기
    @GetMapping()
    public ResponseEntity<Object> getAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return new ResponseEntity<>(new PostDto.PostListResponse(ExceptionCode.POST_GET_OK, posts), HttpStatus.OK);
    }
}
