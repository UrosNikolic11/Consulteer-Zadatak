package com.example.ConsulteerZadatak.service.impl;

import com.example.ConsulteerZadatak.domain.Comment;
import com.example.ConsulteerZadatak.dto.CommentDto;
import com.example.ConsulteerZadatak.exception.BadRequestException;
import com.example.ConsulteerZadatak.exception.NotFoundException;
import com.example.ConsulteerZadatak.mapper.CommentMapper;
import com.example.ConsulteerZadatak.repository.CommentRepository;
import com.example.ConsulteerZadatak.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        Comment comment = commentMapper.map(commentDto);
        commentRepository.save(comment);
        return commentMapper.map(comment);
    }

    @Override
    public Page<CommentDto> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(commentMapper::map);
    }

    @Override
    public CommentDto findById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isEmpty()){
            throw new NotFoundException("There is no comment with given id!");
        }
        return commentMapper.map(comment.get());
    }

    @Override
    public CommentDto update(Long id, CommentDto commentDto) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isEmpty()){
            throw new NotFoundException("There is no comment with given id!");
        }
        if(!(commentDto.getComment() == null)){
            if(commentDto.getComment().equals("")){
                throw new BadRequestException("Comment content is reqiured!");
            }
            comment.get().setComment(commentDto.getComment());
        }
        return commentMapper.map(comment.get());
    }

    @Override
    public void remove(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isEmpty()){
            throw new NotFoundException("There is no comment with given id!");
        }
        commentRepository.deleteById(id);
    }
}
