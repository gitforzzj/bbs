package com.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.entity.Admin;
import com.entity.Board;
import com.entity.Post;
import com.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IBoardService;
import com.service.ILoginService;
import com.service.IPostService;
import com.service.IStudentService;

public class LoginAction extends ActionSupport{

	private Student student;
	private Admin admin;
	private String tip;
	private int pageNo;//当前页
	private int PageSize;//每页条数
	private int count;
	private List<Post> list;
	private int bid;
	private Board board;
	
	@Resource(name="loginService")
	ILoginService loginService;
	
	@Resource(name="postService")
	IPostService postService;
	
	@Resource(name="studentService")
	IStudentService studentService;
	
	@Resource(name="adminService")
	IAdminService adminService;
	
	@Resource(name="boardService")
	IBoardService boardService;

	@Override
	public String execute() throws Exception{
		ActionContext.getContext().getSession().clear();
		Student s = loginService.stuLogin(student);
		if(s!=null){
			ActionContext.getContext().getSession().put("student",s);
			List<Post> result = postService.allPost();
			setList(result);
			return "loginSuccess";
		}
		addActionMessage("登录失败，请输入正确的用户名和密码");
		return INPUT;
	}
	
	public String adminLogin() throws Exception{
		ActionContext.getContext().getSession().clear();
		Admin a = loginService.adminLogin(admin);
		if(a!=null){
			ActionContext.getContext().getSession().put("admin", a);
			List<Post> result = postService.allPost();
			setList(result);
			return "loginSuccess";
		}
		addActionMessage("用户名或密码错误，请重新登录");
		return INPUT;
	}
	
	public String exit(){
		ActionContext.getContext().getSession().clear();
		return "exit";
	}
	
	public String showAll(){
		setPageSize(15);
		HttpServletRequest request = ServletActionContext.getRequest();
		setBoard(boardService.loadBoard(getBid()));
		
		if(request.getParameter("page")!=null){
			setPageNo(Integer.parseInt(request.getParameter("page")));
		}else{
			setPageNo(1);
		}
		
		if(request.getParameter("bid")!=null){
			setBid(Integer.parseInt(request.getParameter("bid")));
		}
		
		try{
			setCount(postService.getMyPostsCount(board));
			setList(postService.pageAllPost(getBid(), getPageNo(), getPageSize()));
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}
	
	public Student getStudent() {
		return student;
	}

	public Admin getAdmin() {
		return admin;
	}

	public String getTip() {
		return tip;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return PageSize;
	}

	public List<Post> getList() {
		return list;
	}

	public int getBid() {
		return bid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Board getBoard() {
		return board;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public void setList(List<Post> list) {
		this.list = list;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
