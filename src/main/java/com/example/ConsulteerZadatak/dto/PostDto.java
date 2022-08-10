package com.example.ConsulteerZadatak.dto;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Integer likes;
    private Integer dislikes;

    public PostDto() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }
}
