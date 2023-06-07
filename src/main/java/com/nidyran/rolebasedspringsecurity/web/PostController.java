package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.PostService;
import com.nidyran.rolebasedspringsecurity.service.model.post.AddPostTDO;
import com.nidyran.rolebasedspringsecurity.service.model.post.PostTDO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Post Resource")
@RequestMapping("/post-configuration")
//@PreAuthorize("@securityService.hasAnyRole('CUSTOMER_AUTHORITY')")
public class PostController {
    private final PostService postService;
    @PostMapping("post/createPost")
    public ResponseEntity<AddPostTDO> createPost(@RequestBody AddPostTDO addPostTDO) {
        AddPostTDO createdPost = postService.createPost(addPostTDO);
        return ResponseEntity.ok(createdPost);
    }

    @PostMapping("/{id}/like")
    public PostTDO likePost(@PathVariable("id") long id, @RequestParam("userId") long userId) {
        return postService.likePost(id, userId);
    }

    @PostMapping("/posts/{id}/dislike")
    public ResponseEntity<PostTDO> dislikePost(@PathVariable long id, @RequestParam long userId) {
        PostTDO dislikedPost = postService.dislikePost(id, userId);
        if (dislikedPost != null) {
            return ResponseEntity.ok(dislikedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/post/removePost/{id}")
    public ResponseEntity<Void> removePost(@PathVariable("id") long id) {
        postService.removePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/post/GetPostByRestaurantId/{restaurantId}")
    public ResponseEntity<List<PostTDO>> getPostsByRestaurantId(@PathVariable("restaurantId") long restaurantId) {
        List<PostTDO> posts = postService.getPostsByRestaurantId(restaurantId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/post/GetAllPost")
    public ResponseEntity<List<PostTDO>> getAllPosts() {
        List<PostTDO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/posts/{id}/likedBy/{userId}")
    public ResponseEntity<Boolean> hasLikedPost(@PathVariable long id, @PathVariable long userId) {
        boolean hasLiked = postService.hasLikedPost(id, userId);
        return ResponseEntity.ok(hasLiked);
    }

}
