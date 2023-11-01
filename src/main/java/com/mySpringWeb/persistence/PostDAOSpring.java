package com.mySpringWeb.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mySpringWeb.domain.board.PostVO;

@Repository
public class PostDAOSpring {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	private final String POST_INSERT = "insert into postlist(board_code, title, content, writer_id, hobby_code, book_id) values(?,?,?,?,?,?)";
	private final String POST_UPDATE = "update postlist set title=?, content=?, book_id=? where id=? "; //수정 
	private final String POST_GET = "select * from postlist where id=?"; //글 하나 가져오기
	private final String POST_LIST = "SELECT\r\n"
			+ "  p.id,\r\n"
			+ "  CASE p.board_code\r\n"
			+ "    WHEN 'BA0' THEN '자유게시판'\r\n"
			+ "    WHEN 'BA1' THEN '취미게시판'\r\n"
			+ "    WHEN 'BA2' THEN '도서게시판'\r\n"
			+ "    ELSE ''\r\n"
			+ "  END AS board_code,\r\n"
			+ "  p.title,\r\n"
			+ "  p.content,\r\n"
			+ "  u.nickname AS writer_id,\r\n"
			+ "  p.view_count,\r\n"
			+ "  CASE p.hobby_code\r\n"
			+ "    WHEN 'HA0' THEN '독서'\r\n"
			+ "    WHEN 'HA1' THEN '여행'\r\n"
			+ "    WHEN 'HA2' THEN '운동'\r\n"
			+ "    ELSE ''\r\n"
			+ "  END AS hobby_code,\r\n"
			+ "  p.book_id,\r\n"
			+ "  p.created_at,\r\n"
			+ "  p.updated_at,\r\n"
			+ "  p.deleted_at\r\n"
			+ "FROM\r\n"
			+ "  postlist p\r\n"
			+ "JOIN\r\n"
			+ "  users u\r\n"
			+ "ON\r\n"
			+ "  p.writer_id = u.id\r\n"
			+ "WHERE\r\n"
			+ "  p.board_code = ?;"; //해당 게시판의 목록 가져오기
	private final String POST_LIST_ALL = "SELECT\r\n"
			+ "  p.id,\r\n"
			+ "  CASE p.board_code\r\n"
			+ "    WHEN 'BA0' THEN '자유게시판'\r\n"
			+ "    WHEN 'BA1' THEN '취미게시판'\r\n"
			+ "    WHEN 'BA2' THEN '도서게시판'\r\n"
			+ "    ELSE ''\r\n"
			+ "  END AS board_code,\r\n"
			+ "  p.title,\r\n"
			+ "  p.content,\r\n"
			+ "  u.nickname AS writer_id,\r\n"
			+ "  p.view_count,\r\n"
			+ "  CASE p.hobby_code\r\n"
			+ "    WHEN 'HA0' THEN '독서'\r\n"
			+ "    WHEN 'HA1' THEN '여행'\r\n"
			+ "    WHEN 'HA2' THEN '운동'\r\n"
			+ "    ELSE ''\r\n"
			+ "  END AS hobby_code,\r\n"
			+ "  p.book_id,\r\n"
			+ "  p.created_at,\r\n"
			+ "  p.updated_at,\r\n"
			+ "  p.deleted_at\r\n"
			+ "FROM\r\n"
			+ "  postlist p\r\n"
			+ "JOIN\r\n"
			+ "  users u\r\n"
			+ "ON\r\n"
			+ "  p.writer_id = u.id;"; //목록 전체 가져오기
	private final String POST_LIST_HOBBY = "SELECT\r\n"
			+ "  p.id,\r\n"
			+ "  CASE p.board_code\r\n"
			+ "    WHEN 'BA0' THEN '자유게시판'\r\n"
			+ "    WHEN 'BA1' THEN '취미게시판'\r\n"
			+ "    WHEN 'BA2' THEN '도서게시판'\r\n"
			+ "    ELSE ''\r\n"
			+ "  END AS board_code,\r\n"
			+ "  p.title,\r\n"
			+ "  p.content,\r\n"
			+ "  u.nickname AS writer_id,\r\n"
			+ "  p.view_count,\r\n"
			+ "  CASE p.hobby_code\r\n"
			+ "    WHEN 'HA0' THEN '독서'\r\n"
			+ "    WHEN 'HA1' THEN '여행'\r\n"
			+ "    WHEN 'HA2' THEN '운동'\r\n"
			+ "    ELSE ''\r\n"
			+ "  END AS hobby_code,\r\n"
			+ "  p.book_id,\r\n"
			+ "  p.created_at,\r\n"
			+ "  p.updated_at,\r\n"
			+ "  p.deleted_at\r\n"
			+ "FROM\r\n"
			+ "  postlist p\r\n"
			+ "JOIN\r\n"
			+ "  users u\r\n"
			+ "ON\r\n"
			+ "  p.writer_id = u.id\r\n"
			+ "WHERE\r\n"
			+ "  p.board_code = 'BA1'\r\n"
			+ "  AND p.hobby_code = 'HA0'";
	
	
	public void insertBoard(PostVO vo) {
        System.out.println("===> Spring JDBC로 insertBoard() 기능처리");
        jdbctemplate.update(POST_INSERT,vo.getBoard_code(), vo.getTitle(), vo.getContent(), vo.getWriter_id(), vo.getHobby_code(), vo.getBook_id());
    }
	
	public void updateBoard(PostVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 기능처리");
		System.out.println("결과값 :"+vo);
		jdbctemplate.update(POST_UPDATE, vo.getTitle(), vo.getContent(), vo.getBook_id(), vo.getId());
	}
	
	public PostVO getBoard(PostVO vo) {
        System.out.println("===> Spring JDBC로 getBoard() 기능처리");
        Object [] args  = {vo.getId()};
        PostVO post = jdbctemplate.queryForObject(POST_GET, new PostRowMapper(), args);
        return post;
    }
	
	public List<PostVO> getBoardList(String board_code) {
        System.out.println("===> Spring JDBC로 getBoardList() 기능처리");
        Object [] args  = {board_code};
        List<PostVO> postList = jdbctemplate.query(POST_LIST, new PostRowMapper(), args);
        return postList;
    }
	
	
}
