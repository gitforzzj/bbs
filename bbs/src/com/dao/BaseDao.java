package com.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import com.entity.Board;

public interface BaseDao<T> {
	
	public T loadById(Class<T> clazz,Serializable id);
	
	public T loadObject(String hql);
	
	public void delById(Class<T> clazz,Serializable id);
	
	public List<T> listAll(String clazz);
	
	public List<T> listAll(String clazz,int pageNo,int pageSize);
	
	public int countAll(String clazz);
	
	public List<T> query(String hql);
	
	public List<T> query(String hql,int pageNo,int pageSize);
	
	public int countQuery(String hql);
	
	public void update(T object);
	
	public  void save(T object);
	
	public void delete(T object);

	int countMyPostAll(String clazz, T object);

	public void saveOrUpdate(T object);
}
