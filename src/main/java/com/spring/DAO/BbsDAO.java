package com.spring.DAO;

import java.util.List;

import com.spring.VO.BbsVO;
import com.spring.VO.FindCriteria;
import com.spring.VO.PageCriteria;

public interface BbsDAO {
	public void insert(BbsVO bvo) throws Exception;
	
	public BbsVO read(Integer bid) throws Exception;
	
	public void update(BbsVO bvo) throws Exception;
	
	public void delete(Integer bid) throws Exception;

	public void boardHit(int bno) throws Exception;
		
	public List<BbsVO> list() throws Exception;
	
	public List<BbsVO> listPage(int page) throws Exception;
	
	public List<BbsVO> listCriteria(PageCriteria cria) throws Exception;
	
	public int countData(PageCriteria pageCria) throws Exception;
	
	public List<BbsVO> listFind(FindCriteria findCri) throws Exception;
	
	public int findCountData(FindCriteria findCri) throws Exception;
	
	
}
