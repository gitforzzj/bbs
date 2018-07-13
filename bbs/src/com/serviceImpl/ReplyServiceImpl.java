package com.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.entity.Post;
import com.entity.Reply;
import com.entity.Student;
import com.service.IReplyService;

public class ReplyServiceImpl implements IReplyService{

	@Resource(name="dao")
	private BaseDao dao;
	
	@Override
	public Reply loadReply(int rid) {
		// TODO Auto-generated method stub
		
		return (Reply) dao.loadById(Reply.class, rid);
	}

	@Override
	public List<Reply> getReplysByPid(int pId) {
		try{
			List<Reply> replys =(List<Reply>)dao.loadObject("from reply as r where r.pid='"+pId+"'");
			return replys;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean stuReplyPost(Student student, Post post, Reply reply) {
		try{
			reply.setStudent(student);
			reply.setPost(post);
			reply.setContent(reply.getContent());
			reply.setPublishTime(new Date());
			dao.save(reply);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean modifyReply(Reply reply) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delReply(int rid) {
		  dao.delById(Reply.class, rid);
		return true;
	}

	@Override
	public int CountReplyByUser(Object user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
