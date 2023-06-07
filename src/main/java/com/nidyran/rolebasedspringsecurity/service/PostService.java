package com.nidyran.rolebasedspringsecurity.service;



import com.nidyran.rolebasedspringsecurity.dao.entity.Post;
import com.nidyran.rolebasedspringsecurity.dao.repository.PostRepository;
import com.nidyran.rolebasedspringsecurity.service.model.post.AddPostTDO;
import com.nidyran.rolebasedspringsecurity.service.model.post.PostTDO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public AddPostTDO createPost(AddPostTDO addPostTDO) {
        Post post = modelMapper.map(addPostTDO, Post.class);
        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, AddPostTDO.class);
    }


    public PostTDO likePost(long id, long userId) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (post.getLikedBy() == null) {
                post.setLikedBy(new HashSet<>());
            }

            Set<Long> likedBy = post.getLikedBy();
            likedBy.add(userId);
            post.setNbLike(likedBy.size());

            Post updatedPost = postRepository.save(post);
            return modelMapper.map(updatedPost, PostTDO.class);
        }
        return null;
    }


    public PostTDO dislikePost(long id, long userId) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (post.getLikedBy() == null) {
                return null;
            }
            Set<Long> likedBy = post.getLikedBy();
            if (likedBy.contains(userId)) {
                likedBy.remove(userId);
                post.setNbLike(likedBy.size());
                Post updatedPost = postRepository.save(post);
                return modelMapper.map(updatedPost, PostTDO.class);
            }
        }
        return null;
    }

    public void removePost(long id) {
        postRepository.deleteById(id);
    }

    public List<PostTDO> getPostsByRestaurantId(long restaurantId) {
        List<Post> posts = postRepository.findByRestaurantId(restaurantId);
        List<Post> sortedPosts = posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
        return sortedPosts.stream()
                .map(post -> modelMapper.map(post, PostTDO.class))
                .collect(Collectors.toList());
    }

    public List<PostTDO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> modelMapper.map(post, PostTDO.class)).collect(Collectors.toList());
    }

    public boolean hasLikedPost(long postId, long userId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            Set<Long> likedBy = post.getLikedBy();
            return likedBy != null && likedBy.contains(userId);
        }
        return false;
    }

}
