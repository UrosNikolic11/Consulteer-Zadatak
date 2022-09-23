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
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto> create(@RequestBody @Valid CreatePostDto createPostDto) {
        return new ResponseEntity<>(postService.create(createPostDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@PathVariable("id") Long id
            , @RequestBody @Valid PostDto postDto) {
        return new ResponseEntity<>(postService.update(id, postDto), HttpStatus.OK);
    }

    @PutMapping("/like/{id}")
    public ResponseEntity<PostDto> like(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.like(id), HttpStatus.OK);
    }

    @PutMapping("/dislike/{id}")
    public ResponseEntity<PostDto> dislike(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.dislike(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> findAll(Pageable pageable) {
        return new ResponseEntity<>(postService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        postService.remove(id);
    }
}
