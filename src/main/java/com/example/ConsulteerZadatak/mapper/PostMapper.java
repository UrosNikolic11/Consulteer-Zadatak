package com.example.ConsulteerZadatak.mapper;

import com.example.ConsulteerZadatak.domain.Post;
import com.example.ConsulteerZadatak.dto.CreatePostDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import com.example.ConsulteerZadatak.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostMapper() {
    }

    public PostDto postToPostDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setLikes(post.getLikes());
        postDto.setDislikes(post.getDislikes());
        return postDto;
    }

    public Post postDtoToPost(PostDto postDto){
        Post post = new Post();
        if(postDto.getTitle().equals("")){
            throw new BadRequestException("Title is required!");
        }
        else post.setTitle(postDto.getTitle());
        if(postDto.getContent().equals("")){
            throw new BadRequestException("Content is required!");
        }
        else post.setContent(postDto.getContent());
        post.setLikes(postDto.getLikes());
        post.setDislikes(postDto.getDislikes());
        return post;
    }

    public Post createPostDtoToPost(CreatePostDto createPostDto){
        Post post = new Post();
        if(createPostDto.getTitle().equals("")){
            throw new BadRequestException("Title is required!");
        }
        else post.setTitle(createPostDto.getTitle());
        if(createPostDto.getContent().equals("")){
            throw new BadRequestException("Content is required!");
        }
        else post.setContent(createPostDto.getContent());
        post.setLikes(0);
        post.setDislikes(0);
        return post;
    }
}
