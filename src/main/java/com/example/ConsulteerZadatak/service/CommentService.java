package com.example.ConsulteerZadatak.service;

import com.example.ConsulteerZadatak.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentDto create(CommentDto commentDto);
    Page<CommentDto> findAll(Pageable pageable);
    CommentDto findById(Long id);
    CommentDto update(Long id, CommentDto commentDto);
    void remove(Long id);
}
