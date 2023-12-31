package com.mySpringWeb.service;

import java.util.List;
import java.util.Map;

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
	public void addCount(String id) {
		postDAO.addCount(id);
	}
	@Override
	public PostVO getBoard(String id) {
		return postDAO.getBoard(id);
	}
	@Override
	public List<PostVO> getBoardList(String board_code) {
		return postDAO.getBoardList(board_code);
	}
	@Override
	public void deleteBoard(String id) {
		postDAO.deleteBoard(id);	
	}
	
	/*새로작성, 토탈 페이지카운트 구하기*/
	@Override
	public int getPageCount(String board_code) {	
		return postDAO.getPageCount(board_code);
	}
	@Override
	public List<PostVO> getList(PostVO searchVO, String board_code) {
		 return postDAO.getList(searchVO, board_code); 
	}
}
