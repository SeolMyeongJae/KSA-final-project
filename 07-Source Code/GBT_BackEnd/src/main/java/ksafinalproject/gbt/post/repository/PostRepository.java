package ksafinalproject.gbt.post.repository;

import ksafinalproject.gbt.post.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleContains(String title, Sort sort);

    List<Post> findAllByAuthorContains(String author, Sort sort);

    List<Post> findAllByCategory(String category, Sort sort);

    List<Post> findAllByUserId(Long userId, Sort sort);
}