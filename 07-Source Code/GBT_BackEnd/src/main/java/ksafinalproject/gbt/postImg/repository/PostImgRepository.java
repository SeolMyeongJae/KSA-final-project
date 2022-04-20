package ksafinalproject.gbt.postImg.repository;

import ksafinalproject.gbt.postImg.model.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImgRepository extends JpaRepository<PostImg, Long> {
    List<PostImg> findAllByPostId(Long postId);
}
