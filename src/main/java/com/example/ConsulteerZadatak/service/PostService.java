package com.example.ConsulteerZadatak.service;

import com.example.ConsulteerZadatak.dto.CreatePostDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
     PostDto createPost(CreatePostDto createPostDto);
     Page<PostDto> findAll(Pageable pageable);
     PostDto findById(Long id);
     PostDto updatePost(Long id, PostDto postDto);
     void remove(Long id);
     PostDto likePost(Long id);
     PostDto dislikePost(Long id);
}
