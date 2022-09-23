package com.example.ConsulteerZadatak.controller;


import com.example.ConsulteerZadatak.dto.CommentDto;
import com.example.ConsulteerZadatak.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDto> create(@RequestBody @Valid CommentDto commentDto) {
        return new ResponseEntity<>(commentService.create(commentDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable("id") Long id
            , @RequestBody @Valid CommentDto commentDto) {
        return new ResponseEntity<>(commentService.update(id, commentDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CommentDto>> findAll(Pageable pageable) {
        return new ResponseEntity<>(commentService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        commentService.remove(id);
    }
}
