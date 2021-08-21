package com.spring.start;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.DAO.BbsDAO;
import com.spring.VO.BbsVO;
import com.spring.VO.FindCriteria;
import com.spring.VO.PageCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BbsDAOTest {
	
	@Inject
	private BbsDAO bdao;
	private static Logger logger = LoggerFactory.getLogger(BbsDAOTest.class);
	
	@Test
	public void testFind() throws Exception {
		FindCriteria cri = new FindCriteria();
		cri.setPage(1);
		cri.setFindType("CW");
		cri.setKeyword("ȫ�浿");
		
		logger.info("******************�� ��� �׽�Ʈ ���***************");
		List<BbsVO> list = bdao.listFind(cri);
		
		for(BbsVO bvo : list) {
			logger.info(bvo.getBid() + ":" + bvo.getSubject());
		}
		logger.info("****************�׽�Ʈ Data���� ���*****************");
		logger.info("CountData : " + bdao.findCountData(cri));
	}
//	@Test
//	public void insertTest() throws Exception {
//		BbsVO bvo = new BbsVO();
//		bvo.setSubject("�׽�Ʈ �����Դϴ�.");
//		bvo.setContent("�׽�Ʈ �����Դϴ�.");
//		bvo.setWriter("ȫ�浿");
//		bdao.insert(bvo);
//	}

//	@Test
//	public void readTest() throws Exception {
//		logger.info(bdao.read(1).toString());
//	}
	
//	@Test
//	public void updateTest() throws Exception {
//		BbsVO bvo = new BbsVO();
//		bvo.setBid(1);
//		bvo.setSubject("subject�����׽�Ʈ");
//		bvo.setContent("content�����׽�Ʈ");
//		bdao.update(bvo);
//	}
	
//	@Test
//	public void deleteTest() throws Exception {
//		bdao.delete(3);
//	}
	
	
//	@Test
//	public void listTest() throws Exception {
//		logger.info(bdao.list().toString());
//	}

//	@Test
//	public void listPageTest() throws Exception {
//		//5�������� ������ ����¡ó�� �׽�Ʈ
//		int page = 5;
//		List<BbsVO> list = bdao.listPage(page);
//		for(BbsVO bbsVO : list) {
//			logger.info(bbsVO.getBid() + ":" + bbsVO.getSubject());
//		}
//	}
	

//	@Test
//	public void listCriteriaTest() throws Exception {
//		PageCriteria pcri = new PageCriteria();
//		pcri.setPage(3);
//		pcri.setNumPerPage(15);
//		
//		List<BbsVO> list = bdao.listCriteria(pcri);
//		
//		for(BbsVO bbsVO : list) {
//			logger.info(bbsVO.getBid()+ ":" + bbsVO.getSubject());
//		}
//	}

	
	//UriComponentsBuilder�� �̿��ϴ� ��� : org.springframework.web.utill ��Ű���� ����.
//	@Test
//	public void uriTest() throws Exception {
//		UriComponents uriComponents = UriComponentsBuilder
//				.newInstance()
//				.path("/start/bbs/read")
//				.queryParam("bid",	100)
//				.queryParam("numPerPage", 20)
//				.build();
//		logger.info("/start/bbs/read?bid=100&numPerPage=20");
//		logger.info(uriComponents.toString());
//		
//		
//	}
	

//	@Test
//	public void uriTest2() throws Exception {
//		UriComponents uriComponents = UriComponentsBuilder
//				.newInstance()
//				.path("/{moduel}/{page}")
//				.queryParam("bid", 100)
//				.queryParam("numPerPage", 20)
//				.build()
//				.expand("start/bbs", "read"); //path�� {boduel}�� {page}�� ������
//		logger.info("/start/bbs/read?bid=100&numPerPage=20");
//		logger.info(uriComponents.toString());
//	}
}
