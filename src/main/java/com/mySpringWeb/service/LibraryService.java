package com.mySpringWeb.service;

import java.util.List;

import com.mySpringWeb.domain.LibraryVO;

public interface LibraryService {
	LibraryVO getLibrary(LibraryVO vo);
	List<LibraryVO> getLibraryList();
}
