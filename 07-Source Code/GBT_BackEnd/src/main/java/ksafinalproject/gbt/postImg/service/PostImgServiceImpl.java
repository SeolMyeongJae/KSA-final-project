package ksafinalproject.gbt.postImg.service;

import ksafinalproject.gbt.post.repository.PostRepository;
import ksafinalproject.gbt.postImg.dto.IPostImg;
import ksafinalproject.gbt.postImg.dto.OPostImg;
import ksafinalproject.gbt.postImg.model.PostImg;
import ksafinalproject.gbt.postImg.repository.PostImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostImgServiceImpl implements PostImgService {

    private final PostImgRepository postImgRepository;
    private final PostRepository postRepository;

    @Override
    public int savePostImg(IPostImg iPostImg) {
        log.info("save post img : {}", iPostImg);
        try {
            postImgRepository.save(PostImg.builder()
                    .id(iPostImg.getId())
                    .url(iPostImg.getUrl())
                    .post(postRepository.findById(iPostImg.getPostId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Transactional
    @Override
    public int updatePostImg(IPostImg iPostImg, Long id) {
        log.info("update post img by id : {}", id);
        try {
            PostImg postImg2 = postImgRepository.findById(id).orElseThrow();
            postImg2.setUrl(iPostImg.getUrl());
            postImg2.setPost(postRepository.findById(iPostImg.getPostId()).orElseThrow());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OPostImg> getPostImgById(Long id) {
        log.info("find post img by id");
        try {
            Optional<PostImg> postImg = postImgRepository.findById(id);
            OPostImg oPostImg = OPostImg.builder()
                    .id(postImg.orElseThrow().getId())
                    .url(postImg.orElseThrow().getUrl())
                    .postId(postImg.orElseThrow().getPost().getId())
                    .build();
            return Optional.of(oPostImg);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OPostImg> getAllPostImg() {
        log.info("find all post img");
        try {
            List<PostImg> postImgList = postImgRepository.findAll();
            List<OPostImg> oPostImgList = new ArrayList<>();
            for (PostImg postImg : postImgList) {
                oPostImgList.add(OPostImg.builder()
                        .id(postImg.getId())
                        .url(postImg.getUrl())
                        .postId(postImg.getPost().getId())
                        .build());
            }
            return oPostImgList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deletePostImgById(Long id) {
        log.info("delete post img by id : {}", id);
        try {
            postImgRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<OPostImg> getAllPostImgByPostId(Long postId) {
        log.info("find all post img by user id : {}", postId);
        try {
            List<PostImg> postImgList = postImgRepository.findAllByPostId(postId);
            List<OPostImg> oPostImgList = new ArrayList<>();
            for (PostImg postImg : postImgList) {
                oPostImgList.add(OPostImg.builder()
                        .id(postImg.getId())
                        .url(postImg.getUrl())
                        .postId(postImg.getPost().getId())
                        .build());
            }
            return oPostImgList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
