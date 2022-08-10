package com.example.ConsulteerZadatak.service.impl;

import com.example.ConsulteerZadatak.domain.Comment;
import com.example.ConsulteerZadatak.domain.Post;
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

    private CommentMapper commentMapper;
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    public Page<CommentDto> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(commentMapper::commentToCommentDto);
    }

    @Override
    public CommentDto findById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent()){
            throw new NotFoundException("There is no comment with given id!");
        }
        return commentMapper.commentToCommentDto(comment.get());
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent()){
            throw new NotFoundException("There is no comment with given id!");
        }
        if(!(commentDto.getComment() == null)){
            if(commentDto.getComment().equals("")){
                throw new BadRequestException("Comment content is reqiured!");
            }
            comment.get().setComment(commentDto.getComment());
        }
        return commentMapper.commentToCommentDto(comment.get());
    }

    @Override
    public void remove(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent()){
            throw new NotFoundException("There is no comment with given id!");
        }
        commentRepository.deleteById(id);
    }
}
