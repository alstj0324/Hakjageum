package com.mySpringWeb.domain.board;

import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Getter @Setter
public class PageVO {
	@Setter
	private int pageIndex = 1;				    //현재페이지
	@Setter
	private int pageUnit = 15;				    //페이지당 출력개수
	@Setter
	private int pageSize = 5;	    			//페이지사이즈
	@Setter
	private int firstIndex = 0;		    		//firstIndex
	@Setter
	private int recordCountPerPage = 15;		//recordCountPerPage
	@Setter
	private int totalCount = 0;				      	//총갯수
	@Setter
	private int startDate = 0;			    	//시작데이터
	@Setter
	private int endDate = 0;				    //종료데이터
	@Setter
    private int realEnd = 0;				    //페이징 마지막 숫자
	@Setter
	private boolean prev, next;	    			//이전,다음버튼

	private String queryString = "";
	  
	  
	public void setQueryString() throws UnsupportedEncodingException {
		String qs = "";
		qs += "&pageIndex="+this.pageIndex;
	
		this.queryString = qs;
	}
}
