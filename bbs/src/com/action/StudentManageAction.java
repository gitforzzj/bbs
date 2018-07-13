package com.action;

import javax.annotation.Resource;

import com.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IStudentService;

public class StudentManageAction extends ActionSupport{
	
	@Resource(name="studentService")
	IStudentService studentService;
	private Student student;
	private String psw;
	private String newPsw;
	private String confirmPsw;
	
	@Override
	public String execute() throws Exception{
		return super.execute();
	}
	
	public String personalStuInfo() throws Exception{
		Student sessionStudent =(Student)ActionContext.getContext().getSession().get("student");
		if(sessionStudent!=null){
			setStudent(studentService.getStudentByStuNum(sessionStudent.getStuNum()));
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String modifyStuInfo() throws Exception{
		Student snStudent =(Student)ActionContext.getContext().getSession().get("student");
		Student s = studentService.getStudentByStuNum(snStudent.getStuNum());
		Student temp = getStudent();
		s.setNickName(temp.getNickName());
		s.setQq(temp.getQq());
		s.setEmail(temp.getEmail());
		if(studentService.modifyStudent(s)){
			return "modifySuccess";
		}
		return ERROR;
	}
	
	public String ModifyPsw() throws Exception{
		Student sessionStudent =(Student)ActionContext.getContext().getSession().get("student");
		Student s = studentService.getStudentByStuNum(sessionStudent.getStuNum());
		if(getPsw().equals(s.getPassword())&&getNewPsw().equals(getConfirmPsw())){
			s.setPassword(getNewPsw());
			studentService.modifyStudent(s);
			addActionMessage("修改成功");
			return "modifyPswSuccess";
		}
		return ERROR;
	}
	
	public IStudentService getStudentService() {
		return studentService;
	}
	public Student getStudent() {
		return student;
	}
	public String getPsw() {
		return psw;
	}
	public String getNewPsw() {
		return newPsw;
	}
	public String getConfirmPsw() {
		return confirmPsw;
	}
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}
	public void setConfirmPsw(String confirmPsw) {
		this.confirmPsw = confirmPsw;
	}
}
