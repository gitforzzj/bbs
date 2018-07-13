package com.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.entity.Post;
import com.entity.Reply;
import com.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IPostService;
import com.service.IReplyService;

public class ReplyAction extends ActionSupport{

	private int pid;
	private Reply reply;
	private List<Reply> replys;
	
	@Resource(name="replyService")
	IReplyService replyService;
	
	@Resource(name="postService")
	IPostService postService;
	
	@Override
	public String execute() throws Exception{
		return super.execute();
	}
	
	public String stuReply() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id=Integer.parseInt(request.getParameter("pid"));
		Student student =(Student)ActionContext.getContext().getSession().get("student");
		try{
			Post post =postService.loadPost(id);
			String aaa=reply.getContent().replaceAll("\\n", "<br/>");
			reply.setContent(aaa);
			replyService.stuReplyPost(student, post, reply);
			setReplys(replyService.getReplysByPid(id));
			request.setAttribute("pid", id);
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}
	
	public String delReply() throws Exception{
		int rid=-1;
		if(ServletActionContext.getRequest().getParameter("rid")!=null){
			rid=Integer.valueOf(ServletActionContext.getRequest().getParameter("rid"));
			replyService.delReply(rid);
			return "success";
		}
		return ERROR;
	}
	
	public int getPid() {
		return pid;
	}
	public Reply getReply() {
		return reply;
	}
	public List<Reply> getReplys() {
		return replys;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}
}
