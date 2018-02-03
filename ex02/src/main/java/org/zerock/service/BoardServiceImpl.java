package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;
/*
 * spring의 bean으로 인식되기 위해 Service애노테이션 적용 
 * 간단한 CRUD작업을 하고 DAO를 이용해서 실행하는 정도
 * */
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	@Transactional
	@Override
	public void regist(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		dao.create(board);
		
		String[] files = board.getFiles();
		
		if(files ==null) {return ;}
		
		for(String fileName : files) {
			dao.addAttach(fileName);
		}
		
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(bno);
		
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}

}
