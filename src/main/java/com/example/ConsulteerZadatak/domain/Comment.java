package com.example.ConsulteerZadatak.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String comment;
    @JoinColumn(name="post_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Post post;

    public Comment() {
    }

    public Comment(Long id, String comment, Post post) {
        this.id = id;
        this.comment = comment;
        this.post = post;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
