package ksafinalproject.gbt.likes.service;

import ksafinalproject.gbt.likes.dto.ILikes;
import ksafinalproject.gbt.likes.dto.OLikes;
import ksafinalproject.gbt.likes.model.Likes;

import java.util.List;
import java.util.Optional;

public interface LikesService {

    int saveLike(ILikes iLike);

    Optional<OLikes> getLikeById(Long id);

    Optional<OLikes> getLikeByPostIdAndUserId(Long postId, Long userId);

    List<OLikes> getAllLike();

    int deleteLikeById(Long id);

    List<OLikes> getAllLikeByPostId(Long postId);

    List<OLikes> getAllLikeByUserId(Long userId);

    Long countLikeByPostId(Long postId);
}
