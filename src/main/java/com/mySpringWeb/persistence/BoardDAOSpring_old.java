package com.mySpringWeb.persistence;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mySpringWeb.domain.BoardVO;

@Repository
public class BoardDAOSpring_old {
    @Autowired
    private JdbcTemplate jdbctemplate;

    private final String BOARD_INSERT = "insert into boards(board_type, title, content, writer_id, book_id, hobby_category) values(?,?,?,?,?,?)";
    private final String BOARD_UPDATE = "update boards set board_type=? title=?, content=?, book_id=? hobby_category=? where board_id=?";
    private final String BOARD_DELETE =	"delete from boards where board_id=?";
    private final String BOARD_GET = "select * from boards where board_id=?";
    private final String BOARD_LIST = "select * from boards order by board_id desc";

    public void insertBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 insertBoard() 기능처리");
        jdbctemplate.update(BOARD_INSERT,vo.getBoard_type(), vo.getTitle(), vo.getContent(), vo.getWriter_id(), vo.getBook_id(), vo.getHobby_category());
    }
    public void updateBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 updateBoard() 기능처리");
        jdbctemplate.update(BOARD_UPDATE, vo.getBoard_type(), vo.getTitle(), vo.getContent(), vo.getBook_id(), vo.getHobby_category(), vo.getBoard_id());
    }
    public void deleteBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 deleteBoard() 기능처리");
        Object [] args = {vo.getBoard_id()};
        jdbctemplate.update(BOARD_DELETE, args);
    }
    public BoardVO getBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 getBoard() 기능처리");
        Object [] args  = {vo.getBoard_id()};
        BoardVO board = jdbctemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
        return board;
    }
    public List<BoardVO> getBoardList() {
        System.out.println("===> Spring JDBC로 getBoardList() 기능처리");
        List<BoardVO> boardList = jdbctemplate.query(BOARD_LIST, new BoardRowMapper());
        return boardList;
    }
}
