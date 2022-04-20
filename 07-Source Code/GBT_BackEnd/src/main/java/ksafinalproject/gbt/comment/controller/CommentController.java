package ksafinalproject.gbt.comment.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.comment.dto.IComment;
import ksafinalproject.gbt.comment.dto.OComment;
import ksafinalproject.gbt.comment.model.Comment;
import ksafinalproject.gbt.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"코멘트"})
@RestController
@CrossOrigin
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public int commentSave(@RequestBody IComment iComment) {
        try {
            return commentService.saveComment(iComment);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int commentUpdate(@RequestBody IComment iComment, @PathVariable Long id) {
        try {
            return commentService.updateComment(iComment, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OComment> commentGetById(@PathVariable Long id) {
        try {
            return commentService.getCommentById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OComment> commentGetAll() {
        try {
            return commentService.getAllComment();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/desc")
    public List<OComment> commentGetAllByDesc() {
        try {
            return commentService.getAllCommentByDesc();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/post/{postId}")
    public List<OComment> commentGetAllByPostId(@PathVariable Long postId) {
        try {
            return commentService.getAllCommentByPostId(postId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int commentDeleteById(@PathVariable Long id) {
        try {
            return commentService.deleteCommentById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }
}
