package com.example.ConsulteerZadatak.repository;

import com.example.ConsulteerZadatak.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepositroy extends JpaRepository<Post, Long> {
    Optional<Post> findPostById(Long id);

}
