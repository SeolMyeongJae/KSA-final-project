package ksafinalproject.gbt.post.service;

import ksafinalproject.gbt.comment.repository.CommentRepository;
import ksafinalproject.gbt.likes.repository.LikesRepository;
import ksafinalproject.gbt.post.dto.IPost;
import ksafinalproject.gbt.post.dto.OPost;
import ksafinalproject.gbt.post.dto.OPostList;
import ksafinalproject.gbt.post.model.Post;
import ksafinalproject.gbt.post.repository.PostRepository;
import ksafinalproject.gbt.user.repository.UserRepository;
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
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;

    @Override
    public int savePost(IPost iPost) {
        log.info("save post : {}", iPost);
        try {
            postRepository.save(Post.builder()
                    .id(iPost.getId())
                    .title(iPost.getTitle())
                    .content(iPost.getContent())
                    .author(iPost.getAuthor())
                    .category(iPost.getCategory())
                    .created(LocalDateTime.now())
                    .user(userRepository.findById(iPost.getUserId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    @Transactional
    public int updatePost(IPost iPost, Long id) {
        log.info("update post : {}, id : {}", iPost, id);
        try {
            Post post2 = postRepository.findById(id).orElseThrow();
            post2.setTitle(iPost.getTitle());
            post2.setContent(iPost.getContent());
            post2.setAuthor(iPost.getAuthor());
            post2.setCategory(iPost.getCategory());
            post2.setUpdated(LocalDateTime.now());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OPost> getPostById(Long id) {
        log.info("find post by id : {}", id);
        try {
            Optional<Post> post = postRepository.findById(id);
            Long likesCount = likesRepository.countByPostId(post.orElseThrow().getId());
            OPost oPost = OPost.builder()
                    .id(post.orElseThrow().getId())
                    .title(post.orElseThrow().getTitle())
                    .content(post.orElseThrow().getContent())
                    .author(post.orElseThrow().getAuthor())
                    .category(post.orElseThrow().getCategory())
                    .created(post.orElseThrow().getCreated())
                    .updated(post.orElseThrow().getCreated())
                    .userId(post.orElseThrow().getUser().getId())
                    .likesCount(likesCount)
                    .postImg(post.orElseThrow().getPostImg())
                    .comment(post.orElseThrow().getComment())
                    .likes(post.orElseThrow().getLikes())
                    .build();
            return Optional.of(oPost);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OPostList> getAllPost() {
        log.info("find all post");
        try {
            List<Post> postList = postRepository.findAll();
            List<OPostList> oPostList = new ArrayList<>();
            for (Post post : postList) {
                Long likesCount = likesRepository.countByPostId(post.getId());
                oPostList.add(OPostList.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .author(post.getAuthor())
                        .category(post.getCategory())
                        .created(post.getCreated())
                        .likesCount(likesCount)
                        .postImg(post.getPostImg())
                        .commentCount(commentRepository.countByPostId(post.getId()))
                        .build());
            }
            return oPostList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OPostList> getAllPostByDesc() {
        log.info("find all post by desc");
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            List<Post> postList = postRepository.findAll(sort);
            List<OPostList> oPostList = new ArrayList<>();
            for (Post post : postList) {
                Long likesCount = likesRepository.countByPostId(post.getId());
                oPostList.add(OPostList.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .author(post.getAuthor())
                        .category(post.getCategory())
                        .created(post.getCreated())
                        .likesCount(likesCount)
                        .postImg(post.getPostImg())
                        .commentCount(commentRepository.countByPostId(post.getId()))
                        .build());
            }
            return oPostList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deletePostById(Long id) {
        log.info("delete post by id : {}", id);
        try {
            postRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<OPostList> getAllPostByUserId(Long userId) {
        log.info("find all post by user id : {}", userId);
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            List<Post> postList = postRepository.findAllByUserId(userId, sort);
            List<OPostList> oPostList = new ArrayList<>();
            for (Post post : postList) {
                Long likesCount = likesRepository.countByPostId(post.getId());
                oPostList.add(OPostList.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .author(post.getAuthor())
                        .category(post.getCategory())
                        .created(post.getCreated())
                        .likesCount(likesCount)
                        .postImg(post.getPostImg())
                        .commentCount(commentRepository.countByPostId(post.getId()))
                        .build());
            }
            return oPostList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OPostList> getAllPostByTitleContains(String title) {
        log.info("find all post by title contains : {}", title);
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            List<Post> postList = postRepository.findAllByTitleContains(title, sort);
            List<OPostList> oPostList = new ArrayList<>();
            for (Post post : postList) {
                Long likesCount = likesRepository.countByPostId(post.getId());
                oPostList.add(OPostList.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .author(post.getAuthor())
                        .category(post.getCategory())
                        .created(post.getCreated())
                        .likesCount(likesCount)
                        .postImg(post.getPostImg())
                        .commentCount(commentRepository.countByPostId(post.getId()))
                        .build());
            }
            return oPostList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OPostList> getAllPostByAuthorContains(String author) {
        log.info("find all post by author contains : {}", author);
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            List<Post> postList = postRepository.findAllByAuthorContains(author, sort);
            List<OPostList> oPostList = new ArrayList<>();
            for (Post post : postList) {
                Long likesCount = likesRepository.countByPostId(post.getId());
                oPostList.add(OPostList.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .author(post.getAuthor())
                        .category(post.getCategory())
                        .created(post.getCreated())
                        .likesCount(likesCount)
                        .postImg(post.getPostImg())
                        .commentCount(commentRepository.countByPostId(post.getId()))
                        .build());
            }
            return oPostList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OPostList> getAllPostByCategory(String category) {
        log.info("find all post by category : {}", category);
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            List<Post> postList = postRepository.findAllByCategory(category, sort);
            List<OPostList> oPostList = new ArrayList<>();
            for (Post post : postList) {
                Long likesCount = likesRepository.countByPostId(post.getId());
                oPostList.add(OPostList.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .author(post.getAuthor())
                        .category(post.getCategory())
                        .created(post.getCreated())
                        .likesCount(likesCount)
                        .postImg(post.getPostImg())
                        .commentCount(commentRepository.countByPostId(post.getId()))
                        .build());
            }
            return oPostList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
