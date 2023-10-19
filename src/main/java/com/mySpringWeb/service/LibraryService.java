package com.mySpringWeb.service;

import java.util.List;

import com.mySpringWeb.domain.LibraryVO;

public interface LibraryService {
	public LibraryVO getLibrary(LibraryVO vo);
	public List<LibraryVO> getLibraryList();
}
