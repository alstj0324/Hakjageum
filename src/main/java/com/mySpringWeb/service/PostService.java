package com.mySpringWeb.service;

import java.util.List;
import com.mySpringWeb.domain.board.PostVO;

public interface PostService {
	public void insertBoard(PostVO vo);
	public void updateBoard(PostVO vo);
	public void deleteBoard(String id);
	public void addCount(String id);
	public PostVO getBoard(String id);
	public List<PostVO> getBoardList(String board_code);
}
