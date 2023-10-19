package com.mySpringWeb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mySpringWeb.domain.LibraryVO;
import com.mySpringWeb.persistence.LibraryDAOSpring;

@Service("libraryService")
public class LibraryServiceimpl implements LibraryService{
	@Autowired
	private LibraryDAOSpring libraryDAO;

	@Override
	public LibraryVO getLibrary(LibraryVO vo) {
		return libraryDAO.getLibrary(vo);
	}
	@Override
	public List<LibraryVO> getLibraryList() {
		return libraryDAO.getLibraryList();
	}
}
