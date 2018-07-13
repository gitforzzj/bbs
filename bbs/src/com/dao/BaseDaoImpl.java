package com.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.entity.Admin;
import com.entity.Board;
import com.entity.Student;

public class BaseDaoImpl<T> implements BaseDao<T>{

	@Resource(name="hibernateTemplate")
	HibernateTemplate hibernateTemplate;
	
	@Override
	public T loadById(Class<T> clazz, Serializable id) {
		return hibernateTemplate.get(clazz, id);
	}

	@Override
	public T loadObject(String hql) {
		final String hql1 = hql;
		T obj = null;
		List<T> list = hibernateTemplate.executeFind(new HibernateCallback<List<T>>(){
			public List<T> doInHibernate(Session session)throws HibernateException{
				Query query =session.createQuery(hql1);
				return query.list();
			}
		});
		if(list.size()>0){
			obj = list.get(0);
		}
		return obj;
	}

	@Override
	public void delById(Class<T> clazz, Serializable id) {
		hibernateTemplate.delete(hibernateTemplate.load(clazz, id));
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll(String clazz) {
		return (List<T>)hibernateTemplate.find("from "+clazz+" as c");
	}

	@Override
	public List<T> listAll(String clazz, int pageNo, int pageSize) {
		final int pSize = pageSize;
		final int pNo = pageNo;
		final String hqlString="from "+clazz+"as c order by c.id desc";
		List<T> list = hibernateTemplate.executeFind(new HibernateCallback<Object>(){
			public List<T> doInHibernate(Session sn)throws HibernateException,SQLException{
				Query query = sn.createQuery(hqlString);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo-1)*pSize);
				List<T> result = query.list();
				if(!Hibernate.isInitialized(result)){
					Hibernate.initialize(result);
				}
				return result;
				
			}
		});
		return list;
	}

	@Override
	public int countAll(String clazz) {
		final String hql ="select count(*) from "+clazz;
		Long count = (Long) hibernateTemplate.execute(new HibernateCallback<Object>(){
			public Object doInHibernate(Session sn)throws HibernateException,SQLException{
				Query query = sn.createQuery(hql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}
	
	@Override
	public int countMyPostAll(String clazz,T object) {
		Long count = null;
		if(object instanceof Student){
			Student ss = (Student) object;
		
			final String hql ="select count(*) from "+clazz+" as p where p.student.id="+ss.getId();
		 		count = (Long) hibernateTemplate.execute(new HibernateCallback<Object>(){
				public Object doInHibernate(Session sn)throws HibernateException,SQLException{
					Query query = sn.createQuery(hql);
					query.setMaxResults(1);
					return query.uniqueResult();
				}
		 	});
		}else if(object instanceof Admin){
			Admin aa = (Admin) object;
		
			final String hql ="select count(*) from "+clazz+" as p where p.admin.id="+aa.getId();
		 		count = (Long) hibernateTemplate.execute(new HibernateCallback<Object>(){
				public Object doInHibernate(Session sn)throws HibernateException,SQLException{
					Query query = sn.createQuery(hql);
					query.setMaxResults(1);
					return query.uniqueResult();
				}
		 	});
		}else{
			Board b = (Board) object;
			final String hql ="select count(*) from "+clazz+" as p where p.board.id="+b.getId();
		 		count = (Long) hibernateTemplate.execute(new HibernateCallback<Object>(){
				public Object doInHibernate(Session sn)throws HibernateException,SQLException{
					Query query = sn.createQuery(hql);
					query.setMaxResults(1);
					return query.uniqueResult();
				}
		 	});
		}
			
		return count.intValue();
	}

	@Override
	public List<T> query(String hql) {
		return hibernateTemplate.find(hql);
	}

	@Override
	public List<T> query(String hql, int pageNo, int pageSize) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String hql1 = hql;
		List<T> list = hibernateTemplate.executeFind(new HibernateCallback<Object>(){
			public List<T> doInHibernate(Session session) throws HibernateException,SQLException{
				Query query = session.createQuery(hql1);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo-1)*pSize);
				List<T> result = query.list();
				return result;
			}
		});
		return list;
	}

	@Override
	public int countQuery(String hql) {
		// TODO Auto-generated method stub
		final String stringhql = hql;
		Long count = (Long) hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException,SQLException{
				Query query = session.createQuery(stringhql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@Override
	public void update(T object) {
		hibernateTemplate.update(object);
	}

	@Override
	public void save(T object) {
		hibernateTemplate.save(object);
	}

	@Override
	public void delete(T object) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(object);
	}

	@Override
	public void saveOrUpdate(T object) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(object);
	}


}
