package com.spring.start;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.VO.PageCriteria;
import com.spring.VO.PagingMaker;
import com.spring.VO.ReplyVO;
import com.spring.service.ReplySvc;


//SqlSession�� ���� �޼ҵ���� ������ ���� �߻�
//��� ��� POST
//��� ó���� REST���(view, JSP���� ���� �����͸� ������ �����),
//REST Client���� URL����� ������ �� REST��Ŀ��� ��� ����� = POST, ��� ���� PUT �Ǵ� PATCH, ��� ����Ʈ �������� GET
//ResponseEntity�� HttpStatus �� 400���� �����ڵ� 300���� �����ڵ� ��
//@RequestBody�� ���۵� �����͸� ��ü�� ��ȯ�����ش�. @ModelAttributes�� �����
@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Inject
	private ReplySvc rsvc;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> input(@RequestBody ReplyVO rvo) {
		
		ResponseEntity<String> resEntity = null;
		
		try {
			rsvc.inputReply(rvo);
			resEntity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			//ResponseEntity�� String���� ���׸�Ÿ���� �����ؼ� String���� ��ȯ�ؾ���. e.getMessage()�� StringŸ���� ��ȯ�ϱ� ������ ��밡��. 
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	//��� ����Ʈ �������� GET
	@RequestMapping(value="/selectAll/{bid}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bid") Integer bid) {
		
		ResponseEntity<List<ReplyVO>> resEntity = null;
		
		try {
			resEntity = new ResponseEntity<List<ReplyVO>>(rsvc.replyList(bid), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	
	//��� ����
	//method�� �迭Ÿ���̿��� JSON�� ��ȯ�ҷ��� @RequestBody�� ���
	@RequestMapping(value="/{rebid}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> modify(@PathVariable("rebid") Integer rebid, 
				@RequestBody ReplyVO rvo) {
		ResponseEntity<String> resEntity = null;
		
		try {
			rvo.setRebid(rebid);
			rsvc.modifyReply(rvo);
			resEntity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	//��� ���� DELETE
	@RequestMapping(value="/{rebid}", method= RequestMethod.DELETE)
	public ResponseEntity<String> reDel(@PathVariable("rebid") Integer rebid) {
		ResponseEntity<String> resEntity = null;
		
		try {
			rsvc.delReply(rebid);
			resEntity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		//���� �߻��� ReplyDAOImpl Ŭ�������� sqlSession.delete�� update�� �ٲ�����
		return resEntity;
	}
	
	//��� ����¡ ó��
	@RequestMapping(value="/{bid}/{page}", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> reListPage(@PathVariable("bid") Integer bid,
															@PathVariable("page") Integer page) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		
		try {
		PageCriteria pCri = new PageCriteria();
		pCri.setPage(page);
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCri);
		
		Map<String, Object> reMap = new HashMap<>();
		List<ReplyVO> reList = rsvc.replyListPage(bid, pCri);
		
		reMap.put("reList", reList);
		int reCount = rsvc.reCount(bid);
		
		pagingMaker.setTotalData(reCount);
		
		reMap.put("pagingMaker", pagingMaker);
		
		resEntity = new ResponseEntity<Map<String,Object>>(reMap, HttpStatus.OK);
		
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
}
