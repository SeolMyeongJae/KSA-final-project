package ksafinalproject.gbt.comment.service;

import ksafinalproject.gbt.comment.dto.IComment;
import ksafinalproject.gbt.comment.dto.OComment;
import ksafinalproject.gbt.comment.model.Comment;
import ksafinalproject.gbt.comment.repository.CommentRepository;
import ksafinalproject.gbt.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public int saveComment(IComment iComment) {
        log.info("save comment : {}", iComment);
        try {
            commentRepository.save(Comment.builder()
                    .id(iComment.getId())
                    .comment(iComment.getComment())
                    .author(iComment.getAuthor())
                    .created(LocalDateTime.now())
                    .post(postRepository.findById(iComment.getPostId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Transactional
    @Override
    public int updateComment(IComment iComment, Long id) {
        log.info("update comment : {}, id : {}", iComment, id);
        try {
            Comment comment2 = commentRepository.findById(id).orElseThrow();
            comment2.setComment(iComment.getComment());
            comment2.setAuthor(iComment.getAuthor());
            comment2.setUpdated(LocalDateTime.now());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OComment> getCommentById(Long id) {
        log.info("find comment by id : {}", id);
        try {
            Optional<Comment> comment = commentRepository.findById(id);
            Long postId = comment.orElseThrow().getPost().getId();
            OComment oComment = OComment.builder()
                    .id(comment.orElseThrow().getId())
                    .author(comment.orElseThrow().getAuthor())
                    .comment(comment.orElseThrow().getComment())
                    .created(comment.orElseThrow().getCreated())
                    .updated(comment.orElseThrow().getUpdated())
                    .postId(postId)
                    .build();
            return Optional.of(oComment);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OComment> getAllComment() {
        log.info("find all comment");
        try {
            List<Comment> commentList = commentRepository.findAll();
            List<OComment> oCommentList = new ArrayList<>();
            for (Comment comment : commentList) {
                Long postId = comment.getPost().getId();
                oCommentList.add(OComment.builder()
                        .id(comment.getId())
                        .author(comment.getAuthor())
                        .comment(comment.getComment())
                        .created(comment.getCreated())
                        .updated(comment.getUpdated())
                        .postId(postId)
                        .build());
            }
            return oCommentList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OComment> getAllCommentByDesc() {
        log.info("find all comment by desc");
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            List<Comment> commentList = commentRepository.findAll(sort);
            List<OComment> oCommentList = new ArrayList<>();
            for (Comment comment : commentList) {
                Long postId = comment.getPost().getId();
                oCommentList.add(OComment.builder()
                        .id(comment.getId())
                        .author(comment.getAuthor())
                        .comment(comment.getComment())
                        .created(comment.getCreated())
                        .updated(comment.getUpdated())
                        .postId(postId)
                        .build());
            }
            return oCommentList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteCommentById(Long id) {
        log.info("delete comment by id : {}", id);
        try {
            commentRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<OComment> getAllCommentByPostId(Long postId) {
        log.info("find all comment by post id : {}", postId);
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            List<Comment> commentList = commentRepository.findAllByPostId(postId, sort);
            List<OComment> oCommentList = new ArrayList<>();
            for (Comment comment : commentList) {
                Long postId2 = comment.getPost().getId();
                oCommentList.add(OComment.builder()
                        .id(comment.getId())
                        .author(comment.getAuthor())
                        .comment(comment.getComment())
                        .created(comment.getCreated())
                        .updated(comment.getUpdated())
                        .postId(postId2)
                        .build());
            }
            return oCommentList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
