package com.example.ConsulteerZadatak.dto;

public final class CommentDto {
    private final Long id;
    private final String comment;
    private final Long postId;

    public CommentDto(Long id, String comment, Long postId) {
        this.id = id;
        this.comment = comment;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Long getPostId() {
        return postId;
    }
}
