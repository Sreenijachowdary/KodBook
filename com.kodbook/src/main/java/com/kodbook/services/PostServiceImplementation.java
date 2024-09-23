package com.kodbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.repositories.PostRepository;

@Service
public class PostServiceImplementation implements PostService{
	
	@Autowired
	PostRepository repo;

	
	public void createPost(Post post) {
		repo.save(post);
		
	}
	public List<Post> fetchAllPosts() {
		return repo.findAll();
	}

	public Post getPost(Long id) {
		return repo.findById(id).get();
	}
	public void updatePost(Post post) {
		repo.save(post);
		
	}
	public List<Post> getAllPosts() {
		return repo.findAll();
	}
	@Override
	public List<Post> fetchPostsByUser(User user) {
		return repo.findByUser(user);
	}
}
