package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;

import com.entity.Admin;
import com.entity.Board;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IBoardService;

public class BoardAction extends ActionSupport{

	@Resource(name="boardService")
	IBoardService boardService;
	
	private List<Board> boardList;
	
	private List<Board> childBoards;
	
	private List<Board> rootBoards;
	
	private Board board;

	private int boardId;
	
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String path;
	
	
	public List<Board> getBoardList() {
		return boardList;
	}

	public List<Board> getChildBoards() {
		return childBoards;
	}

	public List<Board> getRootBoards() {
		return rootBoards;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}

	public void setChildBoards(List<Board> childBoards) {
		this.childBoards = childBoards;
	}

	public void setRootBoards(List<Board> rootBoards) {
		this.rootBoards = rootBoards;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public String loadRootBoards() throws Exception{
		try{
			setRootBoards(boardService.loadRootBoards());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String prepareAddBoard() throws Exception{
		try{
			setBoardList(boardService.loadAllBoards());
			return "addBoard";
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String addBoard() throws Exception{
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/"+getPath()+"/"+this.fileFileName);
		FileOutputStream fos = new FileOutputStream(new File(savePath));
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[1024];
		int length =0;
		while((length=fis.read(b))>0){
			fos.write(b, 0, length);
		}
		Admin admin =(Admin)ServletActionContext.getRequest().getSession().getAttribute("admin");
		//由于多对一关联，这里难以获得parentId，所以通过获取board.id代替parentId传值
		int bid = getBoard().getId();
		Board tempBoard = new Board();
		try{
			if(bid==-1){
				tempBoard.setName(getBoard().getName());
				tempBoard.setAdmin(admin);
				tempBoard.setBoardImg(getFileFileName());
				tempBoard.setDescription(getBoard().getDescription());
				if(boardService.saveOrUpdateBoard(tempBoard)){
					return "addSuccess";
				}
				return ERROR;
			}else{
				Board board = boardService.loadBoard(bid);
				tempBoard.setName(getBoard().getName());
				tempBoard.setAdmin(admin);
				tempBoard.setBoard(board);
				tempBoard.setBoardImg(getFileFileName());
				tempBoard.setDescription(getBoard().getDescription());
				if(boardService.saveOrUpdateBoard(tempBoard)){
					return "addSuccess";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		return ERROR;
	}
	
	public String delBoard() throws Exception{
		if(boardId!=0){
			boardService.delBoard(boardId);
			return "addSuccess";
		}
		return "inputPage";
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public File getFile() {
		return file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public String getPath() {
		return path;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
