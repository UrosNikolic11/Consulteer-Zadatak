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
import static org.mockito.Mockito.when;

class PostServiceImplTest {
    private  PostRepositroy postRepositroy;
    private  CommentRepository commentRepository;
    private  PostMapper postMapper;
    private PostServiceImpl postService;

    @BeforeEach
    void setUp(){
        postRepositroy = Mockito.mock(PostRepositroy.class);
        commentRepository = Mockito.mock(CommentRepository.class);
        postMapper = Mockito.mock(PostMapper.class);
        postService = new PostServiceImpl(postRepositroy, commentRepository, postMapper);
    }

    @Test
    void shouldCreatePost(){
        when(postService.createPost(any(CreatePostDto.class))).
                thenReturn(new PostDto(11L, "PostTitle", "PostContent", 0, 0));

        PostDto postDto = postService.createPost(new CreatePostDto("PostTitle", "PostContent"));
        Assertions.assertEquals(postDto.getId(), 11L);
        Assertions.assertEquals(postDto.getTitle(), "PostTitle");
        Assertions.assertEquals(postDto.getContent(), "PostContent");
        Assertions.assertEquals(postDto.getLikes(), 0);
        Assertions.assertEquals(postDto.getDislikes(), 0);
    }

    @Test
    void shouldUpdatePost(){
        when(postRepositroy.findPostById(any(Long.class))).thenReturn(Optional.of(new Post(2L, "UpdateTitle", "UpdateContent", 0, 0)));
        when(postService.updatePost(2L, new PostDto(2L, "UpdateTitle", "UpdateContent", 0, 0))).
                thenReturn(new PostDto(2L, "UpdateTitle", "UpdateContent", 0, 0));

        PostDto postDto = postService.updatePost(2L, new PostDto(2L, "UpdateTitle", "UpdateContent", 0, 0));
        Assertions.assertEquals(postDto.getId(), 2L);
        Assertions.assertEquals(postDto.getTitle(), "UpdateTitle");
        Assertions.assertEquals(postDto.getContent(), "UpdateContent");
        Assertions.assertEquals(postDto.getLikes(), 0);
        Assertions.assertEquals(postDto.getDislikes(), 0);
    }

    @Test
    void shouldGetPostById(){
        when(postRepositroy.findPostById(any(Long.class)))
                .thenReturn(Optional.of(new Post(2L, "Post", "Content", 0, 0)));
        when(postService.findById(any(Long.class)))
                .thenReturn(new PostDto(2L, "Post", "Content", 0, 0));

        PostDto postDto = postService.findById(2L);
        Assertions.assertEquals(postDto.getId(), 2L);
        Assertions.assertEquals(postDto.getTitle(), "Post");
        Assertions.assertEquals(postDto.getContent(), "Content");
        Assertions.assertEquals(postDto.getLikes(), 0);
        Assertions.assertEquals(postDto.getDislikes(), 0);
    }

    @Test
    void shouldLikePost(){
        when(postRepositroy.findPostById(any(Long.class)))
                .thenReturn(Optional.of(new Post(2L, "Post", "Content", 0, 0)));
        when(postService.likePost(any(Long.class)))
                .thenReturn(new PostDto(2L, "Post", "Content", 1, 0));

        PostDto postDto = postService.likePost(2L);
        Assertions.assertEquals(postDto.getId(), 2L);
        Assertions.assertEquals(postDto.getTitle(), "Post");
        Assertions.assertEquals(postDto.getContent(), "Content");
        Assertions.assertEquals(postDto.getLikes(), 1);
        Assertions.assertEquals(postDto.getDislikes(), 0);
    }

    @Test
    void shouldDisLikePost(){
        when(postRepositroy.findPostById(any(Long.class)))
                .thenReturn(Optional.of(new Post(2L, "Post", "Content", 0, 0)));
        when(postService.dislikePost(any(Long.class)))
                .thenReturn(new PostDto(2L, "Post", "Content", 0, 1));

        PostDto postDto = postService.likePost(2L);
        Assertions.assertEquals(postDto.getId(), 2L);
        Assertions.assertEquals(postDto.getTitle(), "Post");
        Assertions.assertEquals(postDto.getContent(), "Content");
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