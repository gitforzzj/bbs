package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.entity.Board;
import com.service.IBoardService;

public class BoardServiceImpl implements IBoardService{

	@Resource
	private BaseDao dao;
	
	@Override
	public Board loadBoard(int id) {
		// TODO Auto-generated method stub
		return (Board)dao.loadById(Board.class, new Integer(id));
	}

	@Override
	public List<Board> loadChildBoards(int parentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean delBoard(int id){
		 dao.delById(Board.class, id);
		 return true;
	}

	@Override
    public List<Board> loadAllBoards() {
       return dao.listAll("Board");
    }

	@Override
	public List<Board> loadRootBoards() {
		// TODO Auto-generated method stub
		return dao.query("from Board as b where b.board is null order by b.id asc");
	}

	@Override
	public boolean saveOrUpdateBoard(Board board) {
		// TODO Auto-generated method stub
		 dao.saveOrUpdate(board);
		 return true;
	}

}
