package com.example.ConsulteerZadatak.service;

import com.example.ConsulteerZadatak.dto.CommentDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    Page<CommentDto> findAll(Pageable pageable);
    CommentDto findById(Long id);
    CommentDto updateComment(Long id, CommentDto commentDto);
    void remove(Long id);
}
