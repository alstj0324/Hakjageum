package com.mySpringWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mySpringWeb.domain.board.PostVO;
import com.mySpringWeb.persistence.PostDAOSpring;

@Service("postService")
public class PostServiceImpl implements PostService{
	@Autowired
	private PostDAOSpring postDAO;
		
	@Override
	public void insertBoard(PostVO vo) {
		postDAO.insertBoard(vo);
	}
	@Override
	public void updateBoard(PostVO vo) {
		postDAO.updateBoard(vo);
	}
	@Override
	public PostVO getBoard(PostVO vo) {
		return postDAO.getBoard(vo);
	}
	@Override
	public List<PostVO> getBoardList(String board_code) {
		return postDAO.getBoardList(board_code);
	}
	
	
}
