package com.example.ConsulteerZadatak.service;

import com.example.ConsulteerZadatak.dto.CreatePostDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
     PostDto create(CreatePostDto createPostDto);
     Page<PostDto> findAll(Pageable pageable);
     PostDto findById(Long id);
     PostDto update(Long id, PostDto postDto);
     void remove(Long id);
     PostDto like(Long id);
     PostDto dislike(Long id);
}
