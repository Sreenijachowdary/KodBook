package com.kodbook.services;

import java.util.List;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;

public interface PostService {

	void createPost(Post post);

	Post getPost(Long id);

	void updatePost(Post post);
	
	List<Post> getAllPosts();

    List<Post> fetchAllPosts();

	List<Post> fetchPostsByUser(User user);
}
