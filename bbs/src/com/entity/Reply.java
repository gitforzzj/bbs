package com.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Admin admin;
	private Post post;
	private Student student;
	private String content;
	private Date publishTime;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(Post post, String content) {
		this.post = post;
		this.content = content;
	}

	/** full constructor */
	public Reply(Admin admin, Post post, Student student, String content, Date publishTime) {
		this.admin = admin;
		this.post = post;
		this.student = student;
		this.content = content;
		this.publishTime = publishTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date date) {
		this.publishTime = date;
	}

}