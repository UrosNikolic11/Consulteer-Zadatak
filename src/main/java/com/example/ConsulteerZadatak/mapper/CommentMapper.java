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

    public Comment commentDtoToComment(CommentDto commentDto){
        Optional<Post> post = postRepositroy.findPostById(commentDto.getPost_id());
        Comment comment = new Comment();
        if(commentDto.getComment().equals("")){
            throw new BadRequestException("Comment content is reqiured!");
        }
        else comment.setComment(commentDto.getComment());
        if(!post.isPresent()){
            throw new NotFoundException("There is no post with given id.");
        }
        else comment.setPost(post.get());
        return comment;
    }

    public CommentDto commentToCommentDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setComment(comment.getComment());
        commentDto.setPost_id(comment.getPost().getId());
        return commentDto;
    }
}
