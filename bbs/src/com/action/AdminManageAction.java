package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.entity.Admin;
import com.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IStudentService;

public class AdminManageAction extends ActionSupport{
	
	@Resource(name="adminService")
	IAdminService adminService;
	
	@Resource(name="studentService")
	IStudentService studentService;
	
	private Admin admin;
	private int pageSize;
	private int pageNo;
	private int count;
	private List<Student> user;
	private Student student;
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String path;
	private String password1;
	private int sid;
	
	public String execute() throws Exception{
		return super.execute();
	}
	
	public String personalAdminInfo() throws Exception{
		Admin sessionAdmin=(Admin)ActionContext.getContext().getSession().get("admin");
		if(sessionAdmin!=null){
			setAdmin(adminService.loadAdmin(sessionAdmin.getId()));
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String modifyAdminInfo() throws Exception{
		Admin ad = (Admin)ActionContext.getContext().getSession().get("admin");
		ad.setNickName(admin.getNickName());
		ad.setPassword(admin.getPassword());
		if(adminService.modifyAdmin(ad)){
			setAdmin(ad);
			return "modifyInfoSuccess";
		}
		return ERROR;
	}
	
	public String showUser() throws  Exception{
		setPageSize(15);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("page")!=null){
			setPageNo(Integer.parseInt(request.getParameter("page")));
		}else
			setPageNo(1);
		try{
			setCount(studentService.getStudentCount());
			setUser(studentService.pageStudent(pageNo,pageSize));
			return "showUser";
		}catch(Exception e){
			return ERROR;
		}
	}
	
	public String addUser() throws Exception{
		String savePath = ServletActionContext.getServletContext().getRealPath("/"+getPath()+"/"+this.fileFileName);
		FileOutputStream fos = new FileOutputStream(new File(savePath));
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[1024];
		int length =0;
		while((length=fis.read(b))>0){
			fos.write(b, 0, length);
		}
		if(password1.equals(student.getPassword())){
			student.setPhotoPath(fileFileName);
			studentService.saveOrUpdateStudent(student);
			return "operateSuccess";
		}
		addActionError("两次密码不一样，请重新输入");
		return "addFail";
	}
	
	public String modifyUserPage() throws Exception{
		if(sid!=0){
			setStudent(studentService.getStudentById(sid));
			setPassword1(student.getPassword());
			return "modifyPage";
		}
		return "modifyPageFail";
	}
	
	public String modifyUser() throws Exception{
		Student stu = studentService.getStudentByStuNum(student.getStuNum());
		if(file!=null){
			String savePath = ServletActionContext.getServletContext().getRealPath("/"+getPath()+"/"+this.fileFileName);
			FileOutputStream fos = new FileOutputStream(new File(savePath));
			FileInputStream fis = new FileInputStream(file);
			byte[] b = new byte[1024];
			int length =0;
			while((length=fis.read(b))>0){
				fos.write(b, 0, length);
			}
			stu.setPhotoPath(fileFileName);
		}
		if(password1.equals(student.getPassword())){
			stu.setPassword(student.getPassword());
			stu.setRealName(student.getRealName());
			stu.setQq(student.getQq());
			stu.setEmail(student.getEmail());
			stu.setMajor(student.getMajor());
			stu.setClassName(student.getClassName());
			studentService.saveOrUpdateStudent(stu);
			return "operateSuccess";
		}
		return "operateFail";
	}
	
	public String delUser() throws Exception{
		if(sid!=0){
			studentService.delStudentById(sid);
			return "operateSuccess";
		}
		return INPUT;
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public List<Student> getUser() {
		return user;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setUser(List<Student> user) {
		this.user = user;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Student getStudent() {
		return student;
	}

	public File getFile() {
		return file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public String getPath() {
		return path;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	
}
