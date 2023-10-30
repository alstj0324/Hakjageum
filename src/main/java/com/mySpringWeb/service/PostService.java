package com.mySpringWeb.service;

import java.util.List;
import com.mySpringWeb.domain.PostVO;

public interface PostService {
	public void insertBoard(PostVO vo);
	public void updateBoard(PostVO vo);
	public PostVO getBoard(PostVO vo);
	public List<PostVO> getBoardList(String board_code);
}
