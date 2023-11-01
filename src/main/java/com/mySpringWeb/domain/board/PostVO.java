package com.mySpringWeb.domain.board;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostVO  extends PageVO{
    private int id;
    private String board_code;
    private String title;
    private String content;
    private String writer_id;
    private String nickname;
    private int view_count;
    private String hobby_code;
    private String book_id;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;
    //추가
    private int totalCount;
    private String qustr;
    
    public void setQustr() throws UnsupportedEncodingException {
    	
      	String qs = "";
      	this.setQueryString();
      	qs += this.getQueryString();
      	this.qustr = qs;
    }
     
    @Override
    public String toString() {
        return String.format(
            "PostVO[\n" +
            "\tid=%s\n" +
            "\tboard_code=%s\n" +
            "\ttitle=%s\n" +
            "\tcontent=%s\n" +
            "\twriter_id=%s\n" +
            "\tnickname=%s\n" +
            "\tview_count=%s\n" +
            "\thobby_code=%s\n" +
            "\tbook_id=%s\n" +
            "\tcreated_at=%s\n" +
            "\tupdated_at=%s\n" +
            "\tdeleted_at=%s\n" +
            "\ttotalCount=%s\n" +
            "\tpageIndex=%s\n" +
            "\tpageUnit=%s\n" +
            "\tpageSize=%s\n" +
            "\tfirstIndex=%s\n" +
            "\trecordCountPerPage=%s\n" +
            "\tstartDate=%s\n" +
            "\tendDate=%s\n" +
            "\trealEnd=%s\n" +
            "]",
            id, board_code, title, content, writer_id, nickname,view_count, hobby_code, book_id, created_at, updated_at, deleted_at, totalCount,
            this.getPageIndex(), this.getPageUnit(),this.getPageSize(),this.getFirstIndex(),this.getRecordCountPerPage(),this.getStartDate(),this.getEndDate(),this.getRealEnd()
        );
    }
}
