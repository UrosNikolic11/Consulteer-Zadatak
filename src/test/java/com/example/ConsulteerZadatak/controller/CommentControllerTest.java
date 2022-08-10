package com.example.ConsulteerZadatak.controller;

import com.example.ConsulteerZadatak.dto.CommentDto;
import com.example.ConsulteerZadatak.service.CommentService;
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
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void shouldCreateComment() throws Exception {
        when(commentService.createComment(any(CommentDto.class))).thenReturn(new ResponseEntity<>(new CommentDto(11L, "TestComment", 3L), OK).getBody());

        Gson gson = new Gson();
        mvc.perform(post("/comment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(new CommentDto(null, "TestComment", 3L)))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.comment").value("TestComment"))
                .andExpect(jsonPath("$.post_id").value(3L));
    }

    @Test
    void shouldUpdateComment() throws Exception {
        when(commentService.updateComment(any(Long.class),any(CommentDto.class))).thenReturn(new ResponseEntity<>(new CommentDto(2L, "UpdateComment", 3L), OK).getBody());
        Gson gson = new Gson();
        mvc.perform(put("/comment/2").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(new CommentDto(2L, "UpdateComment", 3L))).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("UpdateComment"))
                .andExpect(jsonPath("$.post_id").value(3L));
    }

    @Test
    public void shouldGetPostById() throws Exception
    {
        BDDMockito.given(commentService.findById(2L))
                .willReturn(new ResponseEntity<>(new CommentDto(2L, "GetComment", 3L), OK).getBody());
        mvc.perform( MockMvcRequestBuilders
                        .get("/comment/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L));
    }

    @Test
    void shouldDelete() throws Exception {
        mvc.perform(delete("/comment/delete/2").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}