package com.mySpringWeb.persistence;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mySpringWeb.domain.UserVO;

@Repository
public class UserDAOSpring {
    @Autowired
    private JdbcTemplate jdbctemplate;

    private final String USER_INSERT = "insert into users(id,pwd,nickname,email,provider) values(?,?,?,?,?)";
    private final String USER_UPDATE = "update users set pwd=?, nickname=?, email=?, role_id=? where id=?";
    private final String USER_ROLEUPDATE = "update users set role_id=? where id=?";
    private final String USER_DELETE = "delete from users where id=?";
    private final String USER_GET = "select * from users where id=?";
    private final String USER_LOGIN = "select * from users where id=? and pwd=?";
    private final String USER_LIST = "select * from users";

    public void insertUser(UserVO vo) {
        System.out.println("===>Spring JDBC로 insertUser() 기능처리");
        jdbctemplate.update(USER_INSERT,vo.getId(),vo.getPwd(),vo.getNickname(),vo.getEmail(),vo.getProvider());
    }

    public void updateUser(UserVO vo) {
        System.out.println("===>Spring JDBC로 updateUser() 기능처리");
        jdbctemplate.update(USER_UPDATE,vo.getPwd(),vo.getNickname(),vo.getEmail(), vo.getRole_id(),vo.getId());

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
            UserVO user = jdbctemplate.queryForObject(USER_GET, args, new UserRowMapper());
            String id = user.getId();
            System.out.println("["+id+"]");
            return id;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    public UserVO getUser(UserVO vo) {
        System.out.println("===>Spring JDBC로 getUser() 기능처리");
        try {
            Object [] args  = {vo.getId()};
            return jdbctemplate.queryForObject(USER_GET, args, new UserRowMapper());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public UserVO getUserLogin(UserVO vo) {
        System.out.println("===>Spring JDBC로 getUserLogin() 기능처리");
        try {
            Object [] args  = {vo.getId(), vo.getPwd()};
            return jdbctemplate.queryForObject(USER_LOGIN, args, new UserRowMapper());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    public List<UserVO> getUserList() {
        System.out.println("===>Spring JDBC로 getUserList() 기능처리");
        return jdbctemplate.query(USER_LIST, new UserRowMapper());
    }
}
