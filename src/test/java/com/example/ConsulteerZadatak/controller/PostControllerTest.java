package com.example.ConsulteerZadatak.controller;

import com.example.ConsulteerZadatak.dto.CreatePostDto;
import com.example.ConsulteerZadatak.dto.PostDto;
import com.example.ConsulteerZadatak.service.PostService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
class PostControllerTest {

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void shouldCreatePost() throws Exception {
        when(postService.createPost(any(CreatePostDto.class))).thenReturn(new ResponseEntity<>(new PostDto(11L, "Title", "Content", 0, 0), OK).getBody());

        Gson gson = new Gson();
        mvc.perform(post("/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(new CreatePostDto("Title", "Content")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.content").value("Content"))
                .andExpect(jsonPath("$.likes").value(0))
                .andExpect(jsonPath("$.dislikes").value(0));
    }

    @Test
    void shouldUpdatePost() throws Exception {
        when(postService.updatePost(any(Long.class),any(PostDto.class))).thenReturn(new ResponseEntity<>(new PostDto(2L, "UpdateTitle", "UpdateContent", 0, 0), OK).getBody());
        Gson gson = new Gson();
                mvc.perform(put("/post/2").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(new PostDto(2L, "UpdateTitle", "UpdateContent", 0, 0))).accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.title").value("UpdateTitle"))
                        .andExpect(jsonPath("$.content").value("UpdateContent"))
                        .andExpect(jsonPath("$.likes").value(0))
                        .andExpect(jsonPath("$.dislikes").value(0));
    }

    @Test
    void shouldLikePost()throws Exception{
        when(postService.likePost(any(Long.class))).thenReturn(new ResponseEntity<>(new PostDto(2L, "LikeTitle", "LikeContent", 1, 0), OK).getBody());
        Gson gson = new Gson();
        mvc.perform(put("/post/like/2").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.likes").value(1));
    }

    @Test
    void shouldDisLikePost() throws Exception{
        when(postService.dislikePost(any(Long.class))).thenReturn(new ResponseEntity<>(new PostDto(2L, "DisLikeTitle", "DisLikeContent", 0, 1), OK).getBody());
        Gson gson = new Gson();
        mvc.perform(put("/post/dislike/2").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dislikes").value(1));
    }

    @Test
    public void shouldGetPostById() throws Exception
    {
        PostDto postDto = new PostDto(2L, "Jako dobar klub!!", "Klub je nov i povoljan", 2,1);
        BDDMockito.given(postService.findById(2L))
                .willReturn(new ResponseEntity<>(postDto, OK).getBody());
        mvc.perform( MockMvcRequestBuilders
                        .get("/post/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L));
    }

    @Test
    void shouldDelete() throws Exception {
        mvc.perform(delete("/post/delete/2").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}