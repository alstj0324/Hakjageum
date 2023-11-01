package com.mySpringWeb.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mySpringWeb.domain.board.CommentVO;

@Repository
public class CommentDAOSpring {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	private final String COMMENT_INSERT = "call insert_comment(?, ?, ?)";
	private final String COMMENT_LISTGET = "call get_commentlist(?)";
	private final String COMMENT_DELETE = "call delete_comment(?)";
	
	public void insertComment(CommentVO vo) {
		System.out.println("===> Spring JDBC로 insertComment() 기능처리");
		jdbctemplate.update(COMMENT_INSERT, vo.getPost_id(), vo.getWriter_id(), vo.getContent());
	}
	
	public void deleteComment(String comment_id) {
		System.out.println("===> Spring JDBC로 deleteComment() 기능처리");
		jdbctemplate.update(COMMENT_DELETE, comment_id);
	}
	
	public List<CommentVO> getCommentList(String post_id) {
		System.out.println("===> Spring JDBC로 getCommentList() 기능처리");
		Object [] args  = {post_id};
        List<CommentVO> commentList = jdbctemplate.query(COMMENT_LISTGET, new CommentRowMapper(), args);
        return commentList;
	}
}
