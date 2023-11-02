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

    /*
       parms: board_code
     */
   private final String POST_TOTALCOUNT = "call get_postcount(?)";

    /*
       parms: board_code, limit, offset
     */
   private final String POST_PAGELIST = "call get_postlist_limit(?, ?, ?)";
   /*-----------------------------------------------------------------------------------------*/

   public void insertBoard(PostVO vo) {
        System.out.println("===> Spring JDBC로 insertBoard() 기능처리");
        jdbctemplate.update(POST_INSERT, vo.getTitle(), vo.getContent(), vo.getHobby_code(), vo.getBoard_code(), vo.getWriter_id(), vo.getBook_id() );
    }
   
   public void updateBoard(PostVO vo) {
      System.out.println("===> Spring JDBC로 updateBoard() 기능처리");
      System.out.println("결과값 :"+vo);
      jdbctemplate.update(POST_UPDATE, vo.getId() ,vo.getTitle(), vo.getContent() );
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
