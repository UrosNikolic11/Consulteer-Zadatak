package com.example.ConsulteerZadatak.controller;

import com.example.ConsulteerZadatak.dto.CreatePostDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import com.example.ConsulteerZadatak.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid CreatePostDto createPostDto) {
        return new ResponseEntity<>(postService.createPost(createPostDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") Long id
            , @RequestBody @Valid PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(id, postDto), HttpStatus.OK);
    }

    @PutMapping("/like/{id}")
    public ResponseEntity<PostDto> likePost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.likePost(id), HttpStatus.OK);
    }

    @PutMapping("/dislike/{id}")
    public ResponseEntity<PostDto> dislikePost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.dislikePost(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> allPosts(Pageable pageable) {
        return new ResponseEntity<>(postService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findPost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") Long id){
        postService.remove(id);
    }
}
