package com.mySpringWeb.domain.board;

import lombok.Getter;
import lombok.Setter;

public class Pagination {
	@Getter @Setter
	private int currentPageNo;			//현재 페이지 번호

	@Getter @Setter
	private int recordCountPerPage;		//한 페이지당 게시되는 게시물 수

	@Getter @Setter
	private int pageSize;				//페이지 리스트에 게시되는 페이지 수

	@Getter @Setter
	private int totalRecordCount;		//전체 게시물 수

	@Setter
	private int realEnd;				//페이징 마지막 숫자

	@Setter
	private int firstPageNoOnPageList;	//페이지 리스트의 첫 페이지 번호
	@Setter
	private int lastPageNoOnPageList;	//페이지 리스트의 마지막 페이지 번호
	@Setter
	private int firstRecordIndex; 		//페이징 sql의 조건절에 사용되는 시작 rownum
	@Setter
	private boolean xprev;		//이전버튼
	@Setter
	private boolean xnext;		//다음버튼
	

	public int getFirstPageNoOnPageList() {
	    lastPageNoOnPageList = (int)(Math.ceil(currentPageNo / (double)pageSize)) * pageSize;

	    firstPageNoOnPageList = lastPageNoOnPageList - pageSize + 1;
	    return firstPageNoOnPageList;
	}

	public int getLastPageNoOnPageList() {
	    lastPageNoOnPageList = (int)(Math.ceil(currentPageNo / (double)pageSize)) * pageSize;

	    int realEnd = (int)(Math.ceil((getTotalRecordCount() * 1.0) / getRecordCountPerPage()));

	    if(realEnd < lastPageNoOnPageList) {
	        lastPageNoOnPageList = realEnd;
	    }

	    return lastPageNoOnPageList;
	}

	public int getFirstRecordIndex() {
	    return (getCurrentPageNo() - 1) * getRecordCountPerPage();
	}

	public boolean getXprev() {
		return getFirstPageNoOnPageList() > 1;
	}

	public boolean getXnext() {
		
		int realEnd = (int)(Math.ceil((getTotalRecordCount() * 1.0) / getRecordCountPerPage()));
		
		xnext = getLastPageNoOnPageList() < realEnd;
		return xnext;
	}

	public int getRealEnd() {
		
		realEnd = (int)(Math.ceil((getTotalRecordCount() * 1.0) / getRecordCountPerPage()));
		
		return realEnd;
	}


	public String toString() {
        return String.format(
            "Pagination[\n" +
            "\tcurrentPageNo=%s\n" +
            "\trecordCountPerPage=%s\n" +
            "\tpageSize=%s\n" +
            "\ttotalRecordCount=%s\n" +
            "\trealEnd=%s\n" +
            "\tfirstPageNoOnPageList=%s\n" +
            "\tlastPageNoOnPageList=%s\n" +
            "\tfirstRecordIndex=%s\n" +
            "\txprev=%s\n" +
            "\txnext=%s\n" +
            "]",
            currentPageNo, recordCountPerPage, pageSize, totalRecordCount, realEnd, firstPageNoOnPageList, lastPageNoOnPageList	,firstRecordIndex, xprev, xnext
        );
	}
	
	
}
