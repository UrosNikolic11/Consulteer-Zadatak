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

    public PostDto map(Post post){
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getLikes(), post.getDislikes());
    }

    public Post map(PostDto postDto){
        if(postDto.getTitle().equals("")){
            throw new BadRequestException("Title is required!");
        }
        else if(postDto.getContent().equals("")){
            throw new BadRequestException("Content is required!");
        }
        return new Post(null, postDto.getTitle(), postDto.getContent(), postDto.getLikes(), postDto.getDislikes());
    }

    public Post map(CreatePostDto createPostDto){
        if(createPostDto.getTitle().equals("")){
            throw new BadRequestException("Title is required!");
        }
        if(createPostDto.getContent().equals("")){
            throw new BadRequestException("Content is required!");
        }
        return new Post(null, createPostDto.getTitle(), createPostDto.getContent(), 0, 0);
    }
}
