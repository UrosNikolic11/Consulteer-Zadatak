package com.example.ConsulteerZadatak.repository;

import com.example.ConsulteerZadatak.domain.Comment;
import com.example.ConsulteerZadatak.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
        void deleteAllByPost(Post post);
}
