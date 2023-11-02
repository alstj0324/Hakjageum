package com.mySpringWeb.persistence;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mySpringWeb.domain.board.PostVO;

@Repository
public class PostDAOSpring {
   @Autowired
   private JdbcTemplate jdbctemplate;

   /*
      parms: board_code
    */
   private final String POST_COUNT = "call get_postcount(?)";

   /*
      parms: title, content, board_code, hobby_code, writer_id, book_id
    */
   private final String POST_INSERT = "call insert_post(?, ?, ?, ?, ?, ?)";
   /*
      parms: post_id, title, content
    */
   private final String POST_UPDATE = "call update_post(?, ?, ?)";
   /*
      parms: post_id
    */
   private final String POST_DELETE = "call delete_post(?)";
   /*
      parms: post_id
    */
   private final String POST_VIEW_COUNT = "call inc_viewcount(?)";
   /*
      parms: post_id
    */
   private final String POST_GET = "call get_post(?)";
   /*
      parms: board_code
    */
   private final String POST_LIST = "call get_postlist(?)";
   
   //-----------------------------------------------------------------------------------------추가
   private final String POST_TOTALCOUNT = "select count(*) as totalCount from postlist where board_code = ?;";
   private final String POST_PAGELIST = "SELECT\r\n"
   		+ "  p.id,\r\n"
   		+ "  CASE p.board_code\r\n"
   		+ "    WHEN 'BA0' THEN '자유게시판'\r\n"
   		+ "    WHEN 'BA1' THEN '취미게시판'\r\n"
   		+ "    WHEN 'BA2' THEN '도서게시판'\r\n"
   		+ "    ELSE ''\r\n"
   		+ "  END AS board_code,\r\n"
   		+ "  p.title,\r\n"
   		+ "  p.content,\r\n"
   		+ "  p.writer_id,\r\n"
   		+ "  u.nickname AS nickname,\r\n"
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
   		+ "  p.board_code = ? \r\n"
   		+ "ORDER BY 'id' ASC \r\n"
   		+ "LIMIT ? OFFSET ?;";
   /*-----------------------------------------------------------------------------------------*/

   public void insertBoard(PostVO vo) {
        System.out.println("===> Spring JDBC로 insertBoard() 기능처리");
        jdbctemplate.update(POST_INSERT, vo.getTitle(), vo.getContent(), vo.getHobby_code(), vo.getBoard_code(), vo.getWriter_id(), vo.getBook_id());
    }
   
   public void updateBoard(PostVO vo) {
      System.out.println("===> Spring JDBC로 updateBoard() 기능처리");
      System.out.println("결과값 :"+vo);
      jdbctemplate.update(POST_UPDATE, vo.getTitle(), vo.getContent(), vo.getId());
   }
   
   public void deleteBoard(String id) {
      System.out.println("===> Spring JDBC로 deleteBoard() 기능처리");
      jdbctemplate.update(POST_DELETE, id);
   }
   
   public void addCount(String id) {
      System.out.println("===> Spring JDBC로 addCount() 기능처리");
      jdbctemplate.update(POST_VIEW_COUNT, id);
   }
   
   public PostVO getBoard(String id) {
        System.out.println("===> Spring JDBC로 getBoard() 기능처리");
        Object [] args  = {id};
        return jdbctemplate.queryForObject(POST_GET, new PostRowMapper(), args);
    }
   
   public List<PostVO> getBoardList(String board_code) {
        System.out.println("===> Spring JDBC로 getBoardList() 기능처리");
        Object [] args  = {board_code};
        return jdbctemplate.query(POST_LIST, new PostRowMapper(), args);
    }
   
   public int getPageCount(String board_code) {
	   System.out.println("===> Spring JDBC로 total page 구하기");
	   Object [] args = {board_code};
	   PostVO vo = jdbctemplate.queryForObject(POST_TOTALCOUNT, new PostPageRowMapper(), args);
	   int totalCount = vo.getTotalCount();
	   return totalCount;
   }
  
   public List<PostVO> getList(PostVO searchVO, String board_code){
	   Object [] args = {board_code, searchVO.getRecordCountPerPage(), searchVO.getFirstIndex()};
	   List<PostVO> post = jdbctemplate.query(POST_PAGELIST, new PostRowMapper(), args);
	   return post;
   }
   
}
