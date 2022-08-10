package com.example.ConsulteerZadatak.service.impl;

import com.example.ConsulteerZadatak.domain.Post;
import com.example.ConsulteerZadatak.dto.CreatePostDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import com.example.ConsulteerZadatak.exception.BadRequestException;
import com.example.ConsulteerZadatak.exception.NotFoundException;
import com.example.ConsulteerZadatak.mapper.PostMapper;
import com.example.ConsulteerZadatak.repository.CommentRepository;
import com.example.ConsulteerZadatak.repository.PostRepositroy;
import com.example.ConsulteerZadatak.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepositroy postRepositroy;
    private final CommentRepository commentRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepositroy postRepositroy, CommentRepository commentRepository, PostMapper postMapper) {
        this.postRepositroy = postRepositroy;
        this.commentRepository = commentRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostDto createPost(CreatePostDto createPostDto) {
        Post post = postMapper.createPostDtoToPost(createPostDto);
        postRepositroy.save(post);
        return postMapper.postToPostDto(post);
    }

    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        return postRepositroy.findAll(pageable)
                .map(postMapper::postToPostDto);
    }

    @Override
    public PostDto findById(Long id) {
        Optional<Post> post = postRepositroy.findPostById(id);

        if(!post.isPresent()){
            throw new NotFoundException("There is no post with given id!");
        }
        return postMapper.postToPostDto(post.get());
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Optional<Post> post = postRepositroy.findPostById(id);
        if(!post.isPresent()){
            throw new NotFoundException("There is no post with given id!");
        }
        if(!(postDto.getTitle() == null)){
            if(postDto.getTitle().equals("")){
                throw new BadRequestException("Post title is reqiured!");
            }
            post.get().setTitle(postDto.getTitle());
        }
        if(!(postDto.getContent() == null)){
            if(postDto.getContent().equals("")){
                throw new BadRequestException("Post content is reqiured!");
            }
            post.get().setContent(postDto.getContent());
        }
        if(!(postDto.getLikes() == null)){
            post.get().setLikes(postDto.getLikes());
        }
        if(!(postDto.getDislikes() == null)){
            post.get().setDislikes(postDto.getDislikes());
        }
        postRepositroy.save(post.get());
        return postMapper.postToPostDto(post.get());
    }

    @Override
    public void remove(Long id) {
        Optional<Post> post = postRepositroy.findPostById(id);
        if(!post.isPresent()){
            throw new NotFoundException("There is no post with given id!");
        }
        commentRepository.deleteAllByPost(post.get());
        postRepositroy.deleteById(id);
    }

    @Override
    public PostDto likePost(Long id) {
        Optional<Post> post = postRepositroy.findPostById(id);
        if(!post.isPresent()){
            throw new NotFoundException("There is no post with given id!");
        }
        Post p = post.get();
        p.setLikes(p.getLikes() + 1);
        postRepositroy.save(p);
        return postMapper.postToPostDto(p);
    }

    @Override
    public PostDto dislikePost(Long id) {
        Optional<Post> post = postRepositroy.findPostById(id);
        if(!post.isPresent()){
            throw new NotFoundException("There is no post with given id!");
        }
        Post p = post.get();
        p.setDislikes(p.getDislikes() + 1);
        postRepositroy.save(p);
        return postMapper.postToPostDto(p);
    }
}
