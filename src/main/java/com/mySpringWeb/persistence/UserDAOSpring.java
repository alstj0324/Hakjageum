package com.mySpringWeb.persistence;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mySpringWeb.domain.user.UserVO;

@Repository
public class UserDAOSpring {
    @Autowired
    private JdbcTemplate jdbctemplate;

    private final String USER_INSERT = "insert into users(id,pwd,nickname,email,provider) values(?,?,?,?,?)";
    private final String USER_UPDATE = "update users set pwd=?, nickname=? where id=?";
    private final String USER_POINTUPDATE = "update users set point=? where id=?";
    private final String USER_ROLEUPDATE = "update users set role_id=? where id=?";
    private final String USER_DELETE = "delete from users where id=?";
    private final String USER_GET = "select * from users where id=?";
    /*----1028 Add---for sign in ->nickname check*/
    private final String USER_NICKGET = "select nickname from users where nickname=?";
    /*--------------*/
    private final String USER_LOGIN = "select * from users where id=? and pwd=?";
    private final String USER_LIST = "select * from users";

    public void insertUser(UserVO vo) {
        System.out.println("===>Spring JDBC로 insertUser() 기능처리");
        jdbctemplate.update(USER_INSERT,vo.getId(),vo.getPwd(),vo.getNickname(),vo.getEmail(),vo.getProvider());
    }

    public void updateUser(UserVO vo) {
        System.out.println("===>Spring JDBC로 updateUser() 기능처리");
        jdbctemplate.update(USER_UPDATE,vo.getPwd(),vo.getNickname(),vo.getId());
    }
    public void updatePointUser(UserVO vo) {
    	System.out.println("===>Spring JDBC로 updatePointUser() 기능처리");
    	jdbctemplate.update(USER_POINTUPDATE,vo.getPoint(),vo.getId());
    }
    public void roleupdateUser(UserVO vo) {
        System.out.println("===>Spring JDBC로 roleupdateUser() 기능처리");
        jdbctemplate.update(USER_ROLEUPDATE,vo.getRole_id(),vo.getId());

    }
    public void deleteUser(UserVO vo) {
        System.out.println("===>Spring JDBC로 deleteUser() 기능처리");
        Object [] args = {vo.getId()};
        jdbctemplate.update(USER_DELETE, args);
    }
    public String checkUser (UserVO vo) {
        System.out.println("===>Spring JDBC로 getUserCheck() 기능처리");
        try {
            Object [] args  = {vo.getId()};
            UserVO user = jdbctemplate.queryForObject(USER_GET, new UserRowMapper(), args);
            return user.getId();
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    public String logincheckUser (UserVO vo) {
        System.out.println("===>Spring JDBC로 loginUserCheck() 기능처리");
        try {
            Object [] args  = {vo.getId()};
            jdbctemplate.queryForObject(USER_GET, new UserRowMapper(), args);
            System.out.println("존재하는 아이디 입니다.");          
            return "False";
        }catch(EmptyResultDataAccessException e){
        	System.out.println("존재하지 않는 아이디 입니다."); 
            return "True";
        }
    }
    public String logincheckNickname (UserVO vo) {
    	System.out.println("===>Spring JDBC로 loginUserNick() 기능처리");
    	try {
            Object [] args  = {vo.getNickname()};
            jdbctemplate.queryForObject(USER_NICKGET, new UserRowMapper(), args);
            System.out.println("존재하는 닉네임 입니다.");          
            return "False";
        }catch(EmptyResultDataAccessException e){
        	System.out.println("존재하지 않는 닉네임 입니다.");
            return "True";
        }
    }


    public UserVO getUser(UserVO vo) {
        System.out.println("===>Spring JDBC로 getUser() 기능처리");
        try {
            Object [] args  = {vo.getId()};
            return jdbctemplate.queryForObject(USER_GET, new UserRowMapper(), args);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public UserVO getUser(String userId) {
        System.out.println("===>Spring JDBC로 getUser() 기능처리");
        try {
            Object [] args  = {userId};
            return jdbctemplate.queryForObject(USER_GET, new UserRowMapper(), args);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public UserVO getUserLogin(UserVO vo) {
        System.out.println("===>Spring JDBC로 getUserLogin() 기능처리");
        try {
            Object [] args  = {vo.getId(), vo.getPwd()};
            return jdbctemplate.queryForObject(USER_LOGIN, new UserRowMapper(), args);
        }catch(EmptyResultDataAccessException e){
        	System.out.println("로그인 실패");
            return null;
        }
    }
    public List<UserVO> getUserList() {
        System.out.println("===>Spring JDBC로 getUserList() 기능처리");
        return jdbctemplate.query(USER_LIST, new UserRowMapper());
    }
}
