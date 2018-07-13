package com.serviceImpl;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.entity.Admin;
import com.entity.Student;
import com.service.IAdminService;

public class AdminServiceImpl implements IAdminService{

	@Resource(name="dao")
	BaseDao dao;
	
	@Override
	public Admin loadAdminByAccount(String account){
		Admin a =(Admin)dao.loadObject("from Admin as a where a.account='"+account+"'");
		if(a!=null){
			return a;
		}
		return null;
	}
	
	public boolean modifyAdmin(Admin admin){
		try{
			dao.update(admin);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public Admin loadAdmin(int id) {
		// TODO Auto-generated method stub
		return (Admin)dao.loadById(Admin.class, id);
	}
}
