package com.action;

import java.util.List;

import javax.annotation.Resource;

import com.entity.Board;
import com.entity.Post;
import com.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IBoardService;
import com.service.IPostService;
import com.service.IStudentService;

public class IndexAction extends ActionSupport{

	@Resource(name="studentService")
	IStudentService studentService;
	
	@Resource(name="boardService")
	IBoardService boardService;
	
	@Resource(name="postService")
	IPostService postService;
	
	private List<Board> rootBoard;
	private int todayNum;
	private int yestNum;
	private int highestNum;
	private int total;
	private Student student;
	private List<Post> hotPosts;
	
	@Override
	public String execute() throws Exception{
		try{
			Student sessionStudent = (Student)ActionContext.getContext().getSession().get("student");
			if(sessionStudent!=null){
				setStudent(sessionStudent);
			}else
				student=null;
			setRootBoard(boardService.loadRootBoards());
			int count = postService.countTotalPost();
			if(count>10){
				setHotPosts(postService.rankPosts(10));
			}else
				setHotPosts(postService.rankPosts(count));
			//不用权限判断
			System.out.println(hotPosts.get(0).getBoard().getBoardImg());
			setTotal(count);
			setYestNum(postService.countYesteradyPost());
			setTodayNum(postService.countTodayPost());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public List<Board> getRootBoard() {
		return rootBoard;
	}
	public int getTodayNum() {
		return todayNum;
	}
	public int getYestNum() {
		return yestNum;
	}
	public int getHighestNum() {
		return highestNum;
	}
	public int getTotal() {
		return total;
	}
	public Student getStudent() {
		return student;
	}
	public List<Post> getHotPosts() {
		return hotPosts;
	}
	public void setRootBoard(List<Board> rootBoard) {
		this.rootBoard = rootBoard;
	}
	public void setTodayNum(int todayNum) {
		this.todayNum = todayNum;
	}
	public void setYestNum(int yestNum) {
		this.yestNum = yestNum;
	}
	public void setHighestNum(int highestNum) {
		this.highestNum = highestNum;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setHotPosts(List<Post> hotPosts) {
		this.hotPosts = hotPosts;
	}
	
}
