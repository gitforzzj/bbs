package com.service;

import com.entity.Admin;
import com.entity.Student;

public interface ILoginService {

	public Student stuLogin(Student student);
	
	public Admin adminLogin(Admin admin);
	
}
