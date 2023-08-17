package likelion.Spring_Like_Farmer.post.controller;

import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.post.dto.PostDto;
import likelion.Spring_Like_Farmer.post.service.PostService;
import likelion.Spring_Like_Farmer.security.CurrentUser;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

      // 게시글 올리기 : 글만, 이미지값: null
//    @PostMapping("/save")
//    public ResponseEntity<Object> savePost(@CurrentUser UserPrincipal postPrincipal,
//                                           @RequestBody PostDto.SavePost savePost) {
//        return new ResponseEntity<>(postService.savePost(postPrincipal, savePost, null), HttpStatus.OK);
//    }

     // 게시글 올리기 : 글, 이미지 같이 -> 완료
    @PostMapping("/save")
    public ResponseEntity<Object> savePost(@CurrentUser UserPrincipal userPrincipal,
                                           @ModelAttribute PostDto.SavePost savePost,
                                           @RequestPart(value= "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(postService.savePost(userPrincipal, savePost, file), HttpStatus.OK);
    }

    // 게시물 수정 : 파일만 수정
//    @PatchMapping("/{postId}/file")
//    public ResponseEntity<Object> updatePostFile(@CurrentUser UserPrincipal postPrincipal,
//                                                 @PathVariable Long postId,
//                                                 @RequestPart(value = "file", required = false) MultipartFile file) {
//        return new ResponseEntity<>(postService.updatePost(postPrincipal, postId, file), HttpStatus.OK);
//    }
    // 게시물 수정 : 내용만 수정


    // 게시글 수정 : 내용, 파일 같이 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<Object> updatePostWithFile(@CurrentUser UserPrincipal userPrincipal,
                                                     @PathVariable Long postId,
                                                     @ModelAttribute PostDto.SavePost savePost,
                                                     @RequestPart(value= "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(postService.updatePost(userPrincipal, postId, savePost, file), HttpStatus.OK);
    }

    // 게시글 삭제하기
    @DeleteMapping("/{postId}")
    public ResponseEntity<Object> deletePost(@CurrentUser UserPrincipal userPrincipal,
                                             @PathVariable Long postId) {
        return new ResponseEntity<>(postService.deletePost(userPrincipal, postId), HttpStatus.OK);
    }

    // 게시물 전체 불러오기
    @GetMapping()
    public ResponseEntity<Object> getAllPosts() {
        return new ResponseEntity<>(postService.findAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Object> getPost(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.findPost(postId), HttpStatus.OK);
    }
}
