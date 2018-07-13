package com.service;

import java.util.List;

import com.entity.Post;
import com.entity.Reply;
import com.entity.Student;

public interface IReplyService {
	public Reply loadReply(int rid);
	public List<Reply> getReplysByPid(int pId);
	public boolean stuReplyPost(Student student,Post post,Reply reply);
	public boolean modifyReply(Reply reply);
	public boolean delReply(int rid);
	public int CountReplyByUser(Object user);
}


