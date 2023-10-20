package com.mySpringWeb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mySpringWeb.domain.UserVO;
import com.mySpringWeb.persistence.UserDAOSpring;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAOSpring userDAO;

	@Override
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);
	}
	@Override
	public void updateUser(UserVO vo) {
		userDAO.updateUser(vo);
	}
	@Override
	public void deleteUser(UserVO vo) {
		userDAO.deleteUser(vo);
	}
	@Override
	public String checkUser(UserVO vo) {
		return userDAO.checkUser(vo);
	}
	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}
	@Override
	public UserVO getUserLogin(UserVO vo) {
		return userDAO.getUserLogin(vo);
	}
	@Override
	public List<UserVO> getUserList() {
		return userDAO.getUserList();
	}
}
