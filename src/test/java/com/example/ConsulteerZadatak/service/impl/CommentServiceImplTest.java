package com.example.ConsulteerZadatak.service.impl;

import com.example.ConsulteerZadatak.domain.Comment;
import com.example.ConsulteerZadatak.domain.Post;
import com.example.ConsulteerZadatak.dto.CommentDto;
import com.example.ConsulteerZadatak.mapper.CommentMapper;
import com.example.ConsulteerZadatak.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CommentServiceImplTest {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp(){
        commentRepository = Mockito.mock(CommentRepository.class);
        commentMapper = Mockito.mock(CommentMapper.class);
        commentService = new CommentServiceImpl(commentMapper, commentRepository);
    }

    @Test
    void shouldCreateComment(){
        when(commentService.createComment(any(CommentDto.class))).
                thenReturn(new CommentDto(1L, "Comment", 2L));
        CommentDto commentDto = commentService.createComment(new CommentDto(null, "Comment", 2L));
        Assertions.assertEquals(commentDto.getId(), 1L);
        Assertions.assertEquals(commentDto.getComment(), "Comment");
        Assertions.assertEquals(commentDto.getPost_id(), 2L);
    }

    @Test
    void shouldUpdateComment(){
        when(commentRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Comment(1L, "Comment", new Post(2L, "X", "X", 0, 0))));
        when(commentService.updateComment(1L, new CommentDto(null, "Comment", 2L))).
                thenReturn(new CommentDto(1L, "UpdateComment", 2L));
        CommentDto commentDto = commentService.updateComment(1L, new CommentDto(null, "Comment", 2L));
        Assertions.assertEquals(commentDto.getId(), 1L);
        Assertions.assertEquals(commentDto.getComment(), "UpdateComment");
        Assertions.assertEquals(commentDto.getPost_id(), 2L);
    }

    @Test
    void shouldGetByIdComment(){
        when(commentRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Comment(2L, "Comment", new Post(2L, "X", "X", 0, 0))));
        when(commentService.findById(any(Long.class)))
                .thenReturn(new CommentDto(2L, "Comment", 2L));

        CommentDto commentDto = commentService.findById(2L);
        Assertions.assertEquals(commentDto.getId(), 2L);
        Assertions.assertEquals(commentDto.getComment(), "Comment");
        Assertions.assertEquals(commentDto.getPost_id(), 2L);
    }

    @Test
    void shouldDelete(){
        when(commentRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Comment(2L, "Comment", new Post(2L, "X", "X", 0, 0))));
        commentService.remove(2L);
    }
}