package com.example.ConsulteerZadatak.service.impl;

import com.example.ConsulteerZadatak.domain.Comment;
import com.example.ConsulteerZadatak.domain.Post;
import com.example.ConsulteerZadatak.dto.CommentDto;
import com.example.ConsulteerZadatak.mapper.CommentMapper;
import com.example.ConsulteerZadatak.repository.CommentRepository;
import com.example.ConsulteerZadatak.repository.PostRepositroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CommentServiceImplTest {
    private PostRepositroy postRepositroy;
    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private CommentServiceImpl commentService;
    private static final String COMM = "Comment";
    private static final Post POST = new Post(1L, "PostTitle", "PostContent", 0, 0);
    private static final Comment COMMENT = new Comment(1L, COMM, POST);
    private static final CommentDto COMMENT_DTO = new CommentDto(1L, "Comment", POST.getId());
    private static final CommentDto UPDATE_COMMENT_DTO = new CommentDto(1L, "UpdateComment", POST.getId());

    @BeforeEach
    void setUp(){
        postRepositroy = Mockito.mock(PostRepositroy.class);
        commentRepository = Mockito.mock(CommentRepository.class);
        commentMapper = Mockito.mock(CommentMapper.class);
        commentService = new CommentServiceImpl(commentMapper, commentRepository);
    }

    @Test
    void shouldCreateComment(){
        when(postRepositroy.findPostById(any(Long.class))).thenReturn(Optional.of(POST));
        when(commentMapper.map(COMMENT_DTO)).thenReturn(COMMENT);
        when(commentMapper.map(COMMENT)).thenReturn(COMMENT_DTO);

        CommentDto commentDto = commentService.create(COMMENT_DTO);

        Assertions.assertEquals(commentDto.getId(), 1L);
        Assertions.assertEquals(commentDto.getComment(), COMM);
        Assertions.assertEquals(commentDto.getPostId(), COMMENT_DTO.getPostId());
    }

    @Test
    void shouldUpdateComment(){
        when(commentRepository.findById(any(Long.class))).thenReturn(Optional.of(COMMENT));
        when(commentMapper.map(COMMENT)).thenReturn(UPDATE_COMMENT_DTO);

        CommentDto commentDto = commentService.update(1L, UPDATE_COMMENT_DTO);

        Assertions.assertEquals(commentDto.getId(), 1L);
        Assertions.assertEquals(commentDto.getComment(), "UpdateComment");
        Assertions.assertEquals(commentDto.getPostId(), UPDATE_COMMENT_DTO.getPostId());
    }

    @Test
    void shouldGetByIdComment(){
        when(commentRepository.findById(any(Long.class))).thenReturn(Optional.of(COMMENT));
//        when(commentService.findById(any(Long.class)))
//                .thenReturn(new CommentDto(2L, "Comment", 2L));
        when(commentMapper.map(COMMENT)).thenReturn(COMMENT_DTO);

        CommentDto commentDto = commentService.findById(1L);

        Assertions.assertEquals(commentDto.getId(), 1L);
        Assertions.assertEquals(commentDto.getComment(), COMM);
        Assertions.assertEquals(commentDto.getPostId(), COMMENT_DTO.getPostId());
    }

    @Test
    void shouldDelete(){
        when(commentRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Comment(2L, "Comment", new Post(2L, "X", "X", 0, 0))));
        commentService.remove(2L);
    }
}