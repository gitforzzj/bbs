package com.service;

import java.util.List;

import com.entity.Student;

public interface IStudentService {
	
	public Student getStudentByStuNum(String StuNum);
	public boolean modifyStudent(Student student);
	public int getStudentCount();
	public List<Student> pageStudent(int pageNo, int pageSize);
	public boolean saveOrUpdateStudent(Student student);
	public Student getStudentById(int sid);
	public void delStudentById(int sid);
	
}
