package com.mySpringWeb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mySpringWeb.domain.board.CommentVO;
import com.mySpringWeb.persistence.CommentDAOSpring;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAOSpring commentDAO;

	@Override
	public void insertComment(CommentVO vo) {
		commentDAO.insertComment(vo);	
	}
	@Override
	public void deleteComment(String comment_id) {
		commentDAO.deleteComment(comment_id);
	}
	@Override
	public List<CommentVO> getCommentList(String post_id) {
		return commentDAO.getCommentList(post_id);
	}
	
	
}
