package com.mySpringWeb.service;

import java.util.List;
import com.mySpringWeb.domain.UserVO;

public interface UserService {
	void insertUser(UserVO vo);
	void updateUser(UserVO vo);
	void updatePointUser(UserVO vo);
	void roleupdateUser(UserVO vo);
	void deleteUser(UserVO vo);
	String logincheckUser(UserVO vo);
	String checkUser(UserVO vo);
	UserVO getUser(UserVO vo);
	UserVO getUserLogin(UserVO vo);
	List<UserVO> getUserList();
}
