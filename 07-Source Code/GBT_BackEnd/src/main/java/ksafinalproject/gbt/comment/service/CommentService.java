package ksafinalproject.gbt.comment.service;

import ksafinalproject.gbt.comment.dto.IComment;
import ksafinalproject.gbt.comment.dto.OComment;
import ksafinalproject.gbt.comment.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    int saveComment(IComment iComment);

    int updateComment(IComment iComment, Long id);

    Optional<OComment> getCommentById(Long id);

    List<OComment> getAllComment();

    List<OComment> getAllCommentByDesc();

    int deleteCommentById(Long id);

    List<OComment> getAllCommentByPostId(Long postId);

}
