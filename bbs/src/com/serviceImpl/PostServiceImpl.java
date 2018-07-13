package com.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.entity.Admin;
import com.entity.Post;
import com.entity.Student;
import com.service.IPostService;

public class PostServiceImpl implements IPostService{
	
	@Resource
	private BaseDao dao;

	@Override
	public List<Post> allPost() {
		// TODO Auto-generated method stub
		List<Post> list = dao.listAll("Post");
		return list;
	}

	@Override
	public List<Post> pageAllPost(int bid, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.query("from Post as p where p.board ='"+bid+"' order by p.publishTime desc ", pageNo, pageSize);
	}

	@Override
	public List<Post> pageAllMyPost(Object object, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		 Admin aa;
		 Student ss;
		if(object instanceof Student){
			ss = (Student)object;
			return dao.query("from Post as p where p.student.id ='"+ss.getId()+"' order by p.publishTime desc ", pageNo, pageSize);
		}else{
			aa = (Admin)object;
			return dao.query("from Post as p where p.admin.id ='"+aa.getId()+"' order by p.publishTime desc ", pageNo, pageSize);
		}
	}
	
	@Override
	public List<Post> pagePost(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.query("from Post as p order by p.publishTime desc ", pageNo, pageSize);
	}
	
	@Override
	public int getPostsCount() {
		// TODO Auto-generated method stub
		return dao.countAll("Post");
	}
	@Override
	public int getMyPostsCount(Object object){
		return dao.countMyPostAll("Post",object);
	}

	@Override
	public Post loadPost(int id) {
		return (Post) dao.loadById(Post.class, new Integer(id));
	}

	@Override
	public List<Post> allPostByUser(Object user) {
		if(user instanceof Student){
			Student s=(Student)user;
			List<Post> list =(List<Post>)dao.query("from Post as p where p.student.id ="+s.getId()+" order by p.publishTime desc");
			return list;
		}
		if(user instanceof Admin){
			Admin a=(Admin)user;
			List<Post> list =(List<Post>)dao.query("from Post as p where p.admin.id ="+a.getId());
			return list;
		}
		return null;
	}

	@Override
	public boolean deletePost(Post post) {
		try{
			dao.delete(post);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<Post> searchPosts(String searchKey) {
		// TODO Auto-generated method stub
		return dao.query("from Post as p where p.name like '%"+searchKey+"%'");
	}

	@Override
	public List<Post> rankPosts(int size) {
		List<Post> list = dao.query("from Post as p order by p.count desc ");
		List<Post> result = new ArrayList<Post>();
		
		for(int i=0;i<size;i++){
			result.add(list.get(i));
		}
		return result;
	}

	@Override
	public int countTotalPost() {
		return dao.countAll("Post");
	}

	@Override
	public int countTodayPost() {
		Date todayDate = new Date();
		Date tomorrowDate = new Date(System.currentTimeMillis()+1000*60*60*24);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(todayDate)+" 00:00:00";
		System.out.println(today);
		String tomorrow =sf.format(tomorrowDate)+" 00:00:00";
		System.out.println(tomorrow);
		return dao.countQuery("select count(*) from Post as p where "
				+ "p.publishTime between '"+today+"' and '"+tomorrow+"'");
	}

	@Override
	public int countYesteradyPost() {
		Date todayDate = new Date();
		Date yesterdayDate = new Date(System.currentTimeMillis()-1000*60*60*24);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(todayDate)+" 00:00:00";
		String yesterday = sf.format(yesterdayDate)+" 00:00:00";
		return dao.countQuery("select count(*) from Post as p where "+"p.publishTime between '"+yesterday+"' and '"+today+"'");
	}

	@Override
	public int countDayLargestPost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean save(Post post) {
		try{
			dao.save(post);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	@Override
	public boolean update(Post post) {
		// TODO Auto-generated method stub
		try{
			dao.update(post);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
