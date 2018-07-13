package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.entity.Admin;
import com.entity.Board;
import com.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IBoardService;
import com.service.IStudentService;

public class MyUpload extends ActionSupport{
	
	@Resource(name="studentService")
	IStudentService studentService;
	
	@Resource(name="adminService")
	IAdminService adminService;
	
	@Resource(name="boardService")
	IBoardService boardService;
	
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String path;
	
	private Board board;
	
	@Override
	public String execute() throws Exception{
		String savePath = ServletActionContext.getServletContext().getRealPath("/"+getPath()+"/"+this.fileFileName);
		FileOutputStream fos = new FileOutputStream(new File(savePath));
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[1024];
		int length =0;
		while((length=fis.read(b))>0){
			fos.write(b, 0, length);
		}
		
		Student stu =(Student)ActionContext.getContext().getSession().get("student");
		Admin admin =(Admin)ActionContext.getContext().getSession().get("admin");
		if(stu!=null){
			Student temp = studentService.getStudentByStuNum(stu.getStuNum());
			temp.setPhotoPath(getFileFileName());
			if(studentService.modifyStudent(temp)){
				ActionContext.getContext().getSession().put("student",temp);
				return SUCCESS;
			}
		}
		if(admin!=null){
			Admin atemp = adminService.loadAdminByAccount(admin.getAccount());
			atemp.setPhotoPath(getFileFileName());
			if(adminService.modifyAdmin(atemp)){
				ActionContext.getContext().getSession().put("admin",atemp);
				return SUCCESS;
			}
		}
		return ERROR;
	}
	
	public String boardImgUpload() throws Exception{
		String savePath = ServletActionContext.getServletContext().getRealPath("/"+getPath()+"/"+this.fileFileName);
		FileOutputStream fos = new FileOutputStream(new File(savePath));
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[1024];
		int length =0;
		while((length=fis.read(b))>0){
			fos.write(b, 0, length);
		}
		if(ServletActionContext.getRequest().getParameter("bid")!=null){
			int bid = Integer.valueOf(ServletActionContext.getRequest().getParameter("bid"));
			Board bb = boardService.loadBoard(bid);
			bb.setBoardImg(getFileFileName());
			if(boardService.saveOrUpdateBoard(bb)){
				setBoard(bb);
				return "successImg";
			}
			addActionError("提交失败，请重新提交");
			return "inputPage";
		}
		return "inputPage";
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

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
