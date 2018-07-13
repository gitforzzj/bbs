package com.service;

import java.util.List;

import com.entity.Post;

public interface IPostService {
	
	public List<Post> allPost();
	
	public List<Post> pageAllPost(int bid,int pageNo,int pageSize);
	
	public List<Post> pagePost(int pageNo,int pageSize);
	
	public int getPostsCount();
	
	public Post loadPost(int id);
	//>>怎么通过user加载？
	public	List<Post> allPostByUser(Object user);
	
	public boolean deletePost(Post post);
	
	public List<Post> searchPosts(String searchKey);
	
	public List<Post> rankPosts(int size);
	
	public int countTotalPost();
	
	public int countTodayPost();
	
	public int countYesteradyPost();
	
	public int countDayLargestPost();
	
	public boolean update(Post post);
	
	public boolean save(Post post);

	int getMyPostsCount(Object object);

	List<Post> pageAllMyPost(Object object, int pageNo, int pageSize);
}
