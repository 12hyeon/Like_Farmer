package likelion.Spring_Like_Farmer.post.controller;

import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.post.dto.PostDto;
import likelion.Spring_Like_Farmer.post.service.PostService;
import likelion.Spring_Like_Farmer.security.CurrentUser;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 올리기
    @PostMapping("/save")
    public ResponseEntity<Object> savePost(@CurrentUser UserPrincipal postPrincipal,
                             @RequestBody PostDto.SavePost savePost) {
        return new ResponseEntity<>(postService.savePost(postPrincipal, savePost), HttpStatus.OK);
    }

    // 게시글 수정하기
    @PatchMapping("/{postId}")
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
