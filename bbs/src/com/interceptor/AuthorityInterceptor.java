package com.interceptor;

import java.util.Map;


import com.entity.Admin;
import com.entity.Student;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class AuthorityInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		ActionContext ctx = ai.getInvocationContext();
		Map session = ctx.getSession();
		if(session.get("student")==null&&session.get("admin")==null){
			return Action.LOGIN;
		}else{
			Student stu = (Student)session.get("student");
			Admin admin = (Admin)session.get("admin");
			if(stu!=null){
				if(stu.getNickName()==null){
					return "stPersonInfo";
				}
				return ai.invoke();
			}
			if(admin!=null){
				if(admin.getNickName()==null){
					return "adminPersonalInfo";
				}
				return ai.invoke();
			}
		}
		return Action.LOGIN;
	}
	
}
