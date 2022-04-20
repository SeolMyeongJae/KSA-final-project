package ksafinalproject.gbt.post.service;

import ksafinalproject.gbt.post.dto.IPost;
import ksafinalproject.gbt.post.dto.OPost;
import ksafinalproject.gbt.post.dto.OPostList;
import ksafinalproject.gbt.post.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    int savePost(IPost iPost);

    int updatePost(IPost iPost, Long id);

    Optional<OPost> getPostById(Long id);

    List<OPostList> getAllPost();

    List<OPostList> getAllPostByDesc();

    int deletePostById(Long id);

    List<OPostList> getAllPostByUserId(Long userid);

    List<OPostList> getAllPostByTitleContains(String title);

    List<OPostList> getAllPostByAuthorContains(String author);

    List<OPostList> getAllPostByCategory(String category);

}
