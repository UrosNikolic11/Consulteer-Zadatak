package com.example.ConsulteerZadatak.mapper;

import com.example.ConsulteerZadatak.domain.Post;
import com.example.ConsulteerZadatak.dto.CreatePostDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostMapperTest {

    @Test
    void shouldMapPostDtoToPost(){
        PostDto postDto = new PostDto(1L, "Post", "Post", 0, 0);
        PostMapper postMapper = new PostMapper();
        Post resault = postMapper.map(postDto);
        Assertions.assertEquals(postDto.getTitle(), resault.getTitle());
        Assertions.assertEquals(postDto.getContent(), resault.getContent());
        Assertions.assertEquals(postDto.getLikes(), resault.getLikes());
        Assertions.assertEquals(postDto.getDislikes(), resault.getDislikes());
    }

    @Test
    void shouldMapPostToPostDto(){
        Post post = new Post(1L, "Post", "Post", 0, 0);
        PostMapper postMapper = new PostMapper();
        PostDto resault = postMapper.map(post);
        Assertions.assertEquals(post.getTitle(), resault.getTitle());
        Assertions.assertEquals(post.getContent(), resault.getContent());
        Assertions.assertEquals(post.getLikes(), resault.getLikes());
        Assertions.assertEquals(post.getDislikes(), resault.getDislikes());
    }

    @Test
    void shouldMapCreatePostDtoToPost(){
        CreatePostDto createPostDto = new CreatePostDto("Post", "Post");
        PostMapper postMapper = new PostMapper();
        Post resault = postMapper.map(createPostDto);
        Assertions.assertEquals(createPostDto.getTitle(), resault.getTitle());
        Assertions.assertEquals(createPostDto.getContent(), resault.getContent());
        Assertions.assertEquals(0, resault.getLikes());
        Assertions.assertEquals(0, resault.getDislikes());
    }
}