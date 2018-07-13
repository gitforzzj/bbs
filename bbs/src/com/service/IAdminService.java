package com.service;

import com.entity.Admin;

public interface IAdminService {

	public Admin loadAdmin(int id);
	public Admin loadAdminByAccount(String account);
	public boolean modifyAdmin(Admin admin);
}
