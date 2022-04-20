package ksafinalproject.gbt.postImg.service;

import ksafinalproject.gbt.postImg.dto.IPostImg;
import ksafinalproject.gbt.postImg.dto.OPostImg;
import ksafinalproject.gbt.postImg.model.PostImg;

import java.util.List;
import java.util.Optional;

public interface PostImgService {

    int savePostImg(IPostImg iPostImg);

    int updatePostImg(IPostImg iPostImg, Long id);

    Optional<OPostImg> getPostImgById(Long id);

    List<OPostImg> getAllPostImg();

    int deletePostImgById(Long id);

    List<OPostImg> getAllPostImgByPostId(Long postId);
}
