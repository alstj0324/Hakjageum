package com.mySpringWeb.service;

import java.util.List;
import java.util.Map;


import com.mySpringWeb.domain.board.PostVO;

public interface PostService {
	public void insertBoard(PostVO vo);
	public void updateBoard(PostVO vo);
	public void deleteBoard(String id);
	public void addCount(String id);
	public PostVO getBoard(String id);
	public List<PostVO> getBoardList(String board_code);
	/*새로작성*/
	public int getPageCount(String board_code);
	public List<PostVO> getList(PostVO searchVO);
}
