package com.mySpringWeb.service;

import java.util.List;
import com.mySpringWeb.domain.UserVO;

public interface UserService {
	public void insertUser(UserVO vo);
	public void updateUser(UserVO vo);
	public void deleteUser(UserVO vo);
	public String checkUser(UserVO vo);
	public UserVO getUser(UserVO vo);
	public UserVO getUserLogin(UserVO vo);
	public List<UserVO> getUserList();
}
