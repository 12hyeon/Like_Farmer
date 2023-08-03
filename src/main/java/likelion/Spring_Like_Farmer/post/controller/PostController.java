package likelion.Spring_Like_Farmer.post.controller;

import likelion.Spring_Like_Farmer.post.dto.PostDto;
import likelion.Spring_Like_Farmer.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // Create a new post
    @PostMapping("/create")
    public Long createPost(@RequestBody PostDto.CreatePost request) {
        return postService.createPost(request);
    }
    // Delete a post
    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
