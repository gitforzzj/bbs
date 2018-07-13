package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.entity.Student;
import com.service.IStudentService;

public class StudentServiceImpl implements IStudentService{
	
	@Resource(name="dao")
	BaseDao dao;

	@Override
	public Student getStudentByStuNum(String StuNum) {
		Student s =(Student)dao.loadObject("from Student as a where a.stuNum='"+StuNum+"'");
		if(s!=null){
			return s;
		}
		return null;
	}

	@Override
	public boolean modifyStudent(Student student) {
		try{
			dao.update(student);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	@Override
	public int getStudentCount() {
		return dao.countAll("Student");
	}

	@Override
	public List<Student> pageStudent(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.query("from Student as s order by s.id desc", pageNo, pageSize);
	}

	@Override
	public boolean saveOrUpdateStudent(Student student) {
		// TODO Auto-generated method stub
		dao.saveOrUpdate(student);
		return true;
	}

	@Override
	public Student getStudentById(int sid) {
		// TODO Auto-generated method stub
		return (Student) dao.loadById(Student.class, sid);
	}

	@Override
	public void delStudentById(int sid) {
		// TODO Auto-generated method stub
		dao.delById(Student.class, sid);
	}

}
