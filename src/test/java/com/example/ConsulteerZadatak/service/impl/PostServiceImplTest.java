package com.example.ConsulteerZadatak.service.impl;

import com.example.ConsulteerZadatak.domain.Post;
import com.example.ConsulteerZadatak.dto.CreatePostDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import com.example.ConsulteerZadatak.mapper.PostMapper;
import com.example.ConsulteerZadatak.repository.CommentRepository;
import com.example.ConsulteerZadatak.repository.PostRepositroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostServiceImplTest {
    private  PostRepositroy postRepositroy;
    private  CommentRepository commentRepository;
    private  PostMapper postMapper;
    private PostServiceImpl postService;
    private static final String TITLE = "PostTitle";
    private static final String UPDATE_TITLE = "UpdateTitle";
    private static final String CONTENT = "PostContent";
    private static final String UPDATE_CONTENT = "UpdateContent";
    private static final Post POST = new Post(1L, TITLE, CONTENT, 0, 0);
    private static final PostDto POST_DTO = new PostDto(1L, TITLE, CONTENT, 0, 0);
    private static final PostDto POST_UPDATE_DTO = new PostDto(1L, UPDATE_TITLE, UPDATE_CONTENT, 0, 0);
    private static final CreatePostDto CREATE_POST_DTO = new CreatePostDto(TITLE, CONTENT);



    @BeforeEach
    void setUp(){
        postRepositroy = Mockito.mock(PostRepositroy.class);
        commentRepository = Mockito.mock(CommentRepository.class);
        postMapper = Mockito.mock(PostMapper.class);
        postService = new PostServiceImpl(postRepositroy, commentRepository, postMapper);
    }

    @Test
    void shouldCreatePost(){
        when(postMapper.map(CREATE_POST_DTO)).thenReturn(POST);
        when(postMapper.map(POST)).thenReturn(POST_DTO);

        PostDto postDto = postService.create(CREATE_POST_DTO);

        verify(postRepositroy, times(1)).save(POST);
        Assertions.assertEquals(postDto.getId(), 1L);
        Assertions.assertEquals(postDto.getTitle(), TITLE);
        Assertions.assertEquals(postDto.getContent(), CONTENT);
        Assertions.assertEquals(postDto.getLikes(), 0);
        Assertions.assertEquals(postDto.getDislikes(), 0);
    }

    @Test
    void shouldUpdatePost(){
        when(postRepositroy.findPostById(any(Long.class))).thenReturn(Optional.of(POST));
        when(postMapper.map(POST)).thenReturn(POST_UPDATE_DTO);

        PostDto postDto = postService.update(1L, POST_UPDATE_DTO);

        Assertions.assertEquals(postDto.getId(), 1L);
        Assertions.assertEquals(postDto.getTitle(), UPDATE_TITLE);
        Assertions.assertEquals(postDto.getContent(), UPDATE_CONTENT);
        Assertions.assertEquals(postDto.getLikes(), 0);
        Assertions.assertEquals(postDto.getDislikes(), 0);
    }

    @Test
    void shouldGetPostById(){
        when(postRepositroy.findPostById(any(Long.class))).thenReturn(Optional.of(POST));
        when(postMapper.map(POST)).thenReturn(POST_DTO);

        PostDto postDto = postService.findById(1L);

        Assertions.assertEquals(postDto.getId(), 1L);
        Assertions.assertEquals(postDto.getTitle(), TITLE);
        Assertions.assertEquals(postDto.getContent(), CONTENT);
        Assertions.assertEquals(postDto.getLikes(), 0);
        Assertions.assertEquals(postDto.getDislikes(), 0);
    }

    @Test
    void shouldLikePost(){
        when(postRepositroy.findPostById(any(Long.class))).thenReturn(Optional.of(POST));
        when(postMapper.map(POST)).thenReturn(new PostDto(1L, TITLE, CONTENT, 1, 0));

        PostDto postDto = postService.like(1L);

        Assertions.assertEquals(postDto.getId(), 1L);
        Assertions.assertEquals(postDto.getTitle(), TITLE);
        Assertions.assertEquals(postDto.getContent(), CONTENT);
        Assertions.assertEquals(postDto.getLikes(), 1);
        Assertions.assertEquals(postDto.getDislikes(), 0);
    }

    @Test
    void shouldDisLikePost(){
        when(postRepositroy.findPostById(any(Long.class))).thenReturn(Optional.of(POST));
        when(postMapper.map(POST)).thenReturn(new PostDto(1L, TITLE, CONTENT, 0, 1));

        PostDto postDto = postService.dislike(1L);

        Assertions.assertEquals(postDto.getId(), 1L);
        Assertions.assertEquals(postDto.getTitle(), TITLE);
        Assertions.assertEquals(postDto.getContent(), CONTENT);
        Assertions.assertEquals(postDto.getLikes(), 0);
        Assertions.assertEquals(postDto.getDislikes(), 1);
    }

    @Test
    void shouldDeleteById(){
        when(postRepositroy.findPostById(any(Long.class)))
                .thenReturn(Optional.of(new Post(2L, "Post", "Content", 0, 0)));
        postService.remove(2L);
    }
}