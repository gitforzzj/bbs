package com.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.entity.Admin;
import com.entity.Board;
import com.entity.Post;
import com.entity.Reply;
import com.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IBoardService;
import com.service.IPostService;
import com.service.IStudentService;

public class PostAction extends ActionSupport{
	
	private Post post;
	private List<Post> list;
	private Set<Reply> replies;
	private List<Post> myPosts;
	private Student student;
	private Admin admin;
	private String result;
	private int pageNo;//当前页
	private int pageSize;//每页条数
	private int count;
	private int bid;
	
	@Resource(name="postService")
	IPostService postService;
	@Resource(name="boardService")
	IBoardService boardService;
	@Resource(name="studentService")
	IStudentService studentService;
	@Resource(name="adminService")
	IAdminService adminService;
	
	@Override
	public String execute() throws Exception{
		setPageSize(15);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("page")!=null){
			setPageNo(Integer.parseInt(request.getParameter("page")));
		}else{
			setPageNo(1);
		}
		try{
			setCount(postService.getPostsCount());
			setList(postService.pagePost(pageNo,pageSize));
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
		
	}
	
	public String searchPost() throws Exception{
		try{
			String searchKey = getPost().getName().trim();
			if(searchKey!=""){
				setList(postService.searchPosts(searchKey));
				return "searchSuccess";
			}
			return ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String viewPostsByUser(){
		setPageSize(15);
		HttpServletRequest request = ServletActionContext.getRequest();
		Student s =(Student)ActionContext.getContext().getSession().get("student");
		Admin a = (Admin)ActionContext.getContext().getSession().get("admin");
		if(request.getParameter("page")!=null){
			setPageNo(Integer.parseInt(request.getParameter("page")));
		}else{
			setPageNo(1);
		}
		
		if(s!=null){
			setStudent(studentService.getStudentByStuNum(s.getStuNum()));
			setCount(postService.getMyPostsCount(student));
			setMyPosts(postService.pageAllMyPost(student, pageNo, pageSize));
			return "myposts";
		}
		if(a!=null){
			setAdmin(adminService.loadAdmin(a.getId()));
			setCount(postService.getMyPostsCount(admin));
			setMyPosts(postService.pageAllMyPost(admin, pageNo, pageSize));
			return "myposts";
		}
		return ERROR;
	}
	
	public String viewDetail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession().getAttribute("student")!=null){
			Student s=(Student)request.getSession().getAttribute("student");
			setStudent(studentService.getStudentByStuNum(s.getStuNum()));
		}
		if(request.getSession().getAttribute("admin")!=null){
			Admin a=(Admin)request.getSession().getAttribute("admin");
			setAdmin(admin);
		}
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		Post p = postService.loadPost(pid);
		p.setCount(p.getCount()==null?0:p.getCount()+1);
		postService.update(p);
		setPost(postService.loadPost(pid));
		if(getPost()!=null){
			setReplies(postService.loadPost(pid).getReplies());
			for( Iterator   it = replies.iterator();  it.hasNext(); )
	        {             Reply r = (Reply) it.next();
	        } 
			return "viewDetail";
		}
		return ERROR;
	}
	
	public String preparePost() throws Exception{
		//还可以通过下面这种方式获取
		/*HttpServletRequest request = ServletActionContext.getRequest();
		Student s = (Student)request.getSession().getAttribute("student");*/
		Student s = (Student) ActionContext.getContext().getSession().get("student");
		if(s!=null){
			setStudent(studentService.getStudentByStuNum(s.getStuNum()));
		}
		return "preparePost";
	}
	
	public String modifyPost() throws Exception{
		int pid =-1;
		if(ServletActionContext.getRequest().getParameter("pid")!=null){
			pid = Integer.valueOf(ServletActionContext.getRequest().getParameter("pid"));
			ServletActionContext.getRequest().setAttribute("pid",pid);
			Post tempPost = postService.loadPost(pid);
			tempPost.setName(getPost().getName());
			String content = getPost().getContent().replaceAll("\\n", "<br>");
			tempPost.setContent(content);
			if(postService.update(tempPost)){
				return "modifySuccess";
			}
			return ERROR;
		}
		return ERROR;
	}
	
	public String deletePost() throws Exception{
		int pid =-1;
		if(ServletActionContext.getRequest().getParameter("pid")!=null){
			pid = Integer.valueOf(ServletActionContext.getRequest().getParameter("pid"));
			Post tempPost = postService.loadPost(pid);
			if(postService.deletePost(tempPost)){
				return "deleteSuccess";
			}
			return ERROR;
		}
		return ERROR;
	}
	
	public String addPost() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		int bid=0;
		if(request.getParameter("bid")!=null){
			bid=Integer.valueOf(request.getParameter("bid"));
		}
		Post p = getPost();
		//由于textarea上传的内容显示时需要换行，所以此处将\n转为<br/>
		String content = p.getContent().replaceAll("\\n", "<br/>");
		p.setContent(content);
		Board b = boardService.loadBoard(bid);
		Student s=(Student)ActionContext.getContext().getSession().get("student");
		Admin a =(Admin)ActionContext.getContext().getSession().get("admin");
		if(s!=null){
			p.setStudent(s);
			p.setBoard(b);
			p.setContent(getPost().getContent());
			p.setPublishTime(new Date());
			p.setCount(0);
			postService.save(p);
				post=p;
				return "postSuccess";
		}
		if(a!=null){
			p.setAdmin(a);
			p.setBoard(b);
			p.setContent(getPost().getContent());
			p.setPublishTime(new Date());
			p.setCount(0);
			postService.save(p);
				post=p;
				return "postSuccess";
			
		}
		return ERROR;
	}
	
	public String prepareModify() throws Exception{
		int pid =-1;
		if(ServletActionContext.getRequest().getParameter("pid")!=null){
			pid=Integer.valueOf(ServletActionContext.getRequest().getParameter("pid"));
		}
		setPost(postService.loadPost(pid));
		return "prepareSuccess";
	}
	
	public Post getPost() {
		return post;
	}
	public List<Post> getList() {
		return list;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public List<Post> getMyPosts() {
		return myPosts;
	}
	public Student getStudent() {
		return student;
	}
	public Admin getAdmin() {
		return admin;
	}
	public String getResult() {
		return result;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public void setList(List<Post> list) {
		this.list = list;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public void setMyPosts(List<Post> myPosts) {
		this.myPosts = myPosts;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getCount() {
		return count;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
