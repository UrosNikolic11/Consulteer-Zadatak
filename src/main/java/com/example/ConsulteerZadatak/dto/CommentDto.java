package com.example.ConsulteerZadatak.dto;

public class CommentDto {
    private Long id;
    private String comment;
    private Long post_id;

    public CommentDto() {
    }

    public CommentDto(Long id, String comment, Long post_id) {
        this.id = id;
        this.comment = comment;
        this.post_id = post_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }
}
