package com.example.ConsulteerZadatak.dto;

public final class PostDto {
    private final Long id;
    private final String title;
    private final String content;
    private final Integer likes;
    private final Integer dislikes;


    public PostDto(Long id, String title, String content, Integer likes, Integer dislikes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }
}
