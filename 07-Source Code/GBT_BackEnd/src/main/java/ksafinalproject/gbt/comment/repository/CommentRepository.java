package ksafinalproject.gbt.comment.repository;

import ksafinalproject.gbt.comment.model.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId, Sort sort);

    Long countByPostId(Long postId);
}
