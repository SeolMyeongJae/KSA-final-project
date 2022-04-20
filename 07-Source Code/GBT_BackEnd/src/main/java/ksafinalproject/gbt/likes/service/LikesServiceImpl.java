package ksafinalproject.gbt.likes.service;

import ksafinalproject.gbt.likes.dto.ILikes;
import ksafinalproject.gbt.likes.dto.OLikes;
import ksafinalproject.gbt.likes.model.Likes;
import ksafinalproject.gbt.likes.repository.LikesRepository;
import ksafinalproject.gbt.post.repository.PostRepository;
import ksafinalproject.gbt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public int saveLike(ILikes iLike) {
        log.info("save like : {}", iLike);
        try {
            Optional<Likes> like2 = likeRepository.findByPostIdAndUserId(iLike.getPostId(), iLike.getUserId());
            if (like2.isPresent()) {
                likeRepository.deleteById(like2.get().getId());
                return 2;
            }
            likeRepository.save(Likes.builder()
                    .id(iLike.getId())
                    .post(postRepository.findById(iLike.getPostId()).orElseThrow())
                    .user(userRepository.findById(iLike.getUserId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OLikes> getLikeById(Long id) {
        log.info("find like by id : {}", id);
        try {
            Optional<Likes> likes = likeRepository.findById(id);
            OLikes oLikes = OLikes.builder()
                    .id(likes.orElseThrow().getId())
                    .postId(likes.orElseThrow().getPost().getId())
                    .userid(likes.orElseThrow().getUser().getId())
                    .build();
            return Optional.of(oLikes);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<OLikes> getLikeByPostIdAndUserId(Long postId, Long userId) {
        log.info("find like by post id : {}, user id : {}", postId, userId);
        try {
            Optional<Likes> likes = likeRepository.findByPostIdAndUserId(postId, userId);
            OLikes oLikes = OLikes.builder()
                    .id(likes.orElseThrow().getId())
                    .postId(likes.orElseThrow().getPost().getId())
                    .userid(likes.orElseThrow().getUser().getId())
                    .build();
            return Optional.of(oLikes);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OLikes> getAllLike() {
        log.info("find all like");
        try {
            List<Likes> likesList = likeRepository.findAll();
            List<OLikes> oLikesList = new ArrayList<>();
            for (Likes likes : likesList) {
                oLikesList.add(OLikes.builder()
                        .id(likes.getId())
                        .postId(likes.getPost().getId())
                        .userid(likes.getUser().getId())
                        .build());
            }
            return oLikesList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteLikeById(Long id) {
        log.info("delete like by id");
        try {
            likeRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<OLikes> getAllLikeByPostId(Long postId) {
        log.info("find all like by post id : {}", postId);
        try {
            List<Likes> likesList = likeRepository.findAllByPostId(postId);
            List<OLikes> oLikesList = new ArrayList<>();
            for (Likes likes : likesList) {
                oLikesList.add(OLikes.builder()
                        .id(likes.getId())
                        .postId(likes.getPost().getId())
                        .userid(likes.getUser().getId())
                        .build());
            }
            return oLikesList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OLikes> getAllLikeByUserId(Long userId) {
        log.info("find all like by user id : {}", userId);
        try {
            List<Likes> likesList = likeRepository.findAllByUserId(userId);
            List<OLikes> oLikesList = new ArrayList<>();
            for (Likes likes : likesList) {
                oLikesList.add(OLikes.builder()
                        .id(likes.getId())
                        .postId(likes.getPost().getId())
                        .userid(likes.getUser().getId())
                        .build());
            }
            return oLikesList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Long countLikeByPostId(Long postId) {
        log.info("count like by post id : {}", postId);
        try {
            return likeRepository.countByPostId(postId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
