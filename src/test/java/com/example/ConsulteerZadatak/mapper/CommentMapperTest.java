package com.example.ConsulteerZadatak.mapper;

import com.example.ConsulteerZadatak.domain.Comment;
import com.example.ConsulteerZadatak.domain.Post;
import com.example.ConsulteerZadatak.dto.CommentDto;
import com.example.ConsulteerZadatak.repository.PostRepositroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

class CommentMapperTest {
    private PostRepositroy postRepositroy;
    private CommentMapper commentMapper;

    private Post post = new Post(2L, "Title", "Content", 0, 0);
    @BeforeEach
    public void setUp() {

        postRepositroy = Mockito.mock(PostRepositroy.class);
        commentMapper = new CommentMapper(postRepositroy);
        Mockito.when(postRepositroy.findPostById(any(Long.class)))
                .thenReturn(Optional.of(post));
    }
    @Test
    void dtoToOriginalTest(){
        Comment comment = new Comment(1L, "Comment", post);
        CommentDto commentDto = new CommentDto(1L, "Comment", 2L);

        Comment test = commentMapper.commentDtoToComment(commentDto);
        Assertions.assertEquals(comment.getComment(), test.getComment());
        Assertions.assertEquals(comment.getPost(), test.getPost());
    }

    @Test
    void originalTodDto(){
        Comment comment = new Comment(1L, "Comment", new Post(1L, "Title", "Content", 0, 0));
        CommentDto commentDto = new CommentDto(1L, "Comment", 1L);
        CommentMapper commentMapper = new CommentMapper(postRepositroy);
        CommentDto test = commentMapper.commentToCommentDto(comment);
        Assertions.assertEquals(commentDto.getComment(), test.getComment());
        Assertions.assertEquals(commentDto.getPost_id(), test.getPost_id());
    }

}