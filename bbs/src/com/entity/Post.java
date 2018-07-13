package com.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Post entity. @author MyEclipse Persistence Tools
 */

public class Post implements java.io.Serializable {

	// Fields

	private Integer id;
	private Admin admin;
	private Student student;
	private Board board;
	private String name;
	private String content;
	private Date publishTime;
	private Integer count;
	private Set replies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Post() {
	}

	/** minimal constructor */
	public Post(Board board, String name) {
		this.board = board;
		this.name = name;
	}

	/** full constructor */
	public Post(Admin admin, Student student, Board board, String name, String content, Timestamp publishTime,
			Integer count, Set replies) {
		this.admin = admin;
		this.student = student;
		this.board = board;
		this.name = name;
		this.content = content;
		this.publishTime = publishTime;
		this.count = count;
		this.replies = replies;
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

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

}