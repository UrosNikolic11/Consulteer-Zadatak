package com.example.ConsulteerZadatak.mapper;

import com.example.ConsulteerZadatak.domain.Post;
import com.example.ConsulteerZadatak.dto.CreatePostDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostMapperTest {

    @Test
    void dtoToOriginalTest(){
        Post post = new Post(1L, "Post", "Post", 0, 0);
        PostDto postDto = new PostDto(1L, "Post", "Post", 0, 0);
        PostMapper postMapper = new PostMapper();
        Post test = postMapper.postDtoToPost(postDto);
        Assertions.assertEquals(post.getTitle(), test.getTitle());
        Assertions.assertEquals(post.getContent(), test.getContent());
        Assertions.assertEquals(post.getLikes(), test.getLikes());
        Assertions.assertEquals(post.getDislikes(), test.getDislikes());
    }

    @Test
    void originalToDtoTest(){
        Post post = new Post(1L, "Post", "Post", 0, 0);
        PostDto postDto = new PostDto(1L, "Post", "Post", 0, 0);
        PostMapper postMapper = new PostMapper();
        PostDto test = postMapper.postToPostDto(post);
        Assertions.assertEquals(postDto.getTitle(), test.getTitle());
        Assertions.assertEquals(postDto.getContent(), test.getContent());
        Assertions.assertEquals(postDto.getLikes(), test.getLikes());
        Assertions.assertEquals(postDto.getDislikes(), test.getDislikes());
    }

    @Test
    void createDtoToOriginalTest(){
        Post post = new Post(1L, "Post", "Post", 0, 0);
        CreatePostDto postDto = new CreatePostDto("Post", "Post");
        PostMapper postMapper = new PostMapper();
        Post test = postMapper.createPostDtoToPost(postDto);
        Assertions.assertEquals(post.getTitle(), test.getTitle());
        Assertions.assertEquals(post.getContent(), test.getContent());
        Assertions.assertEquals(post.getLikes(), test.getLikes());
        Assertions.assertEquals(post.getDislikes(), test.getDislikes());
    }
}