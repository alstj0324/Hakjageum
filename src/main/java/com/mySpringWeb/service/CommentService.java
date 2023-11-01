package com.mySpringWeb.service;

import java.util.List;
import com.mySpringWeb.domain.board.CommentVO;

public interface CommentService {
	public void insertComment(CommentVO vo);
	public void deleteComment(String comment_id);
	public List<CommentVO> getCommentList(String post_id);
}
