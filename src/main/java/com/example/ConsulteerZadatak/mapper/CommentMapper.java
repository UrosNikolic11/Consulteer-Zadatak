package com.example.ConsulteerZadatak.mapper;

import com.example.ConsulteerZadatak.domain.Comment;
import com.example.ConsulteerZadatak.domain.Post;
import com.example.ConsulteerZadatak.dto.CommentDto;
import com.example.ConsulteerZadatak.exception.BadRequestException;
import com.example.ConsulteerZadatak.exception.NotFoundException;
import com.example.ConsulteerZadatak.repository.PostRepositroy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentMapper {

    private final PostRepositroy postRepositroy;

    public CommentMapper(PostRepositroy postRepositroy) {
        this.postRepositroy = postRepositroy;
    }

    public Comment map(CommentDto commentDto){
        Optional<Post> post = postRepositroy.findPostById(commentDto.getPostId());
        if(commentDto.getComment().equals("")){
            throw new BadRequestException("Comment content is reqiured!");
        }
        if(post.isEmpty()){
            throw new NotFoundException("There is no post with given id.");
        }
        return new Comment(null, commentDto.getComment(), post.get());
    }

    public CommentDto map(Comment comment){
        return new CommentDto(comment.getId(), comment.getComment(), comment.getPost().getId());
    }
}
