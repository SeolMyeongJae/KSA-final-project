package ksafinalproject.gbt.likes.repository;

import ksafinalproject.gbt.likes.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByPostIdAndUserId(Long postId, Long userId);

    List<Likes> findAllByPostId(Long postId);

    List<Likes> findAllByUserId(Long userId);

    Long countByPostId(Long postId);
}
