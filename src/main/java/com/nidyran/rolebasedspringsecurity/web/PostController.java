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

    @PutMapping("post/LikePost/{id}/like")
    public ResponseEntity<PostTDO> likePost(@PathVariable("id") long id) {
        PostTDO postTDO = postService.likePost(id);
        if (postTDO != null) {
            return ResponseEntity.ok(postTDO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("post/DislikePost/{id}/dislike")
    public ResponseEntity<PostTDO> dislikePost(@PathVariable("id") long id) {
        PostTDO postTDO = postService.dislikePost(id);
        if (postTDO != null) {
            return ResponseEntity.ok(postTDO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("post/removePost/{id}")
    public ResponseEntity<Void> removePost(@PathVariable("id") long id) {
        postService.removePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("post/GetPostByRestaurantId/{restaurantId}")
    public ResponseEntity<List<PostTDO>> getPostsByRestaurantId(@PathVariable("restaurantId") long restaurantId) {
        List<PostTDO> posts = postService.getPostsByRestaurantId(restaurantId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("post/GetAllPost")
    public ResponseEntity<List<PostTDO>> getAllPosts() {
        List<PostTDO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}
