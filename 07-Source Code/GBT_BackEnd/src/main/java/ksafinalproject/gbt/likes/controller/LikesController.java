package ksafinalproject.gbt.likes.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.likes.dto.ILikes;
import ksafinalproject.gbt.likes.dto.OLikes;
import ksafinalproject.gbt.likes.model.Likes;
import ksafinalproject.gbt.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"라이크"})
@RestController
@CrossOrigin
@RequestMapping("/api/likes")
@RequiredArgsConstructor
@Slf4j
public class LikesController {

    private final LikesService likeService;

    @PostMapping("")
    public int likeSave(@RequestBody ILikes iLike) {
        try {
            return likeService.saveLike(iLike);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OLikes> likeGetById(@PathVariable Long id) {
        try {
            return likeService.getLikeById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/post/{postId}/user/{userId}")
    public Optional<OLikes> likeGetByPostIdAndUserId(@PathVariable Long postId, @PathVariable Long userId) {
        try {
            return likeService.getLikeByPostIdAndUserId(postId, userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OLikes> likeGetAll() {
        try {
            return likeService.getAllLike();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int likeDeleteById(@PathVariable Long id) {
        try {
            return likeService.deleteLikeById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/all/post/{postId}")
    public List<OLikes> likeGetAllByPostId(@PathVariable Long postId) {
        try {
            return likeService.getAllLikeByPostId(postId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/user/{userId}")
    public List<OLikes> likeGetAllByUserId(@PathVariable Long userId) {
        try {
            return likeService.getAllLikeByUserId(userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/count/post/{postId}")
    public Long likeCountByPostId(@PathVariable Long postId) {
        try {
            return likeService.countLikeByPostId(postId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
