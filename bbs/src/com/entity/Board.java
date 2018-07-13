package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Board entity. @author MyEclipse Persistence Tools
 */

public class Board implements java.io.Serializable {

	// Fields

	private Integer id;
	private Admin admin;
	private Board board;
	private String name;
	private String description;
	private String boardImg;
	private Set boards = new HashSet(0);
	private Set posts = new HashSet(0);

	// Constructors

	/** default constructor */
	public Board() {
	}

	/** minimal constructor */
	public Board(Admin admin, String name) {
		this.admin = admin;
		this.name = name;
	}

	/** full constructor */
	public Board(Admin admin, Board board, String name, String description, String boardImg, Set boards, Set posts) {
		this.admin = admin;
		this.board = board;
		this.name = name;
		this.description = description;
		this.boardImg = boardImg;
		this.boards = boards;
		this.posts = posts;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBoardImg() {
		return this.boardImg;
	}

	public void setBoardImg(String boardImg) {
		this.boardImg = boardImg;
	}

	public Set getBoards() {
		return this.boards;
	}

	public void setBoards(Set boards) {
		this.boards = boards;
	}

	public Set getPosts() {
		return this.posts;
	}

	public void setPosts(Set posts) {
		this.posts = posts;
	}

}