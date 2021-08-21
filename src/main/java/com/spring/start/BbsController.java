package com.spring.start;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.VO.BbsVO;
import com.spring.VO.PageCriteria;
import com.spring.VO.PagingMaker;
import com.spring.service.BbsService;

@Controller
@RequestMapping("/bbs/*") 
//Ŭ���� �տ� RequestMapping�� ���� ���
//@Model�� Ű�� ������ �����͸� �����Ѵ�. ��, �����͸� �����ϰ��� �Ҷ� ����Ѵ�. 
//GET����� url�� ���ؼ� �����͸� �����ϴϱ� �ܺο� ������ �Ǿ� �Ǵ� ��쿡 ����Ѵ� �Է�������, ��ȸ ������ 
//���� ������ �Է��ϴ� ��쿡 ����ϰ� url�� ������ �������� �ʵ����ϱ� ���� ����Ѵ�. �ܺο� ����Ǹ� �ȵǴ� ���� POST����� �̿��Ѵ�.
//String�� ���� �並 �����ְڴ�
//public String writePost(BbsVO bvo, Model model) throws Exception { 
//RedirectAttributes��ü�� �����̷�Ʈ ������ �ѹ��� ���Ǵ� �����͸� ������ �� �ִ� addFlashAttribute()�� �����Ѵ�.(url�����͸�����)
public class BbsController {
	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Inject
	private BbsService bsvc;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(Model model) throws Exception {
		logger.info("�۸�� ��������............");
		model.addAttribute("list", bsvc.list());
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void writeGET(BbsVO bvo, Model model) throws Exception {
		logger.info("write GET....");
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePost(BbsVO bvo, RedirectAttributes reAttr) throws Exception { 
		logger.info("write POST....");
		logger.info(bvo.toString());
		bsvc.write(bvo);
		//model.addAttribute("result", "success");
		//1���� ����ϱ� ������ url�� �����Ͱ� ������� ����
		reAttr.addFlashAttribute("result", "success");
		
		return "redirect:/bbs/list";
	}
	
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void read(@RequestParam("bid") int bid, @ModelAttribute("pCri") PageCriteria pCri, Model model) throws Exception {
		
		
		model.addAttribute(bsvc.read(bid));
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modifyGET(@RequestParam("bid") int bid, @ModelAttribute("pCri") PageCriteria pCri, Model model) throws Exception {
		model.addAttribute(bsvc.read(bid));
		
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPOST(BbsVO bvo, PageCriteria pCri , RedirectAttributes reAttr) throws Exception {
		logger.info("modifyPOST().........");
		bsvc.modfiy(bvo);
		
		reAttr.addAttribute("page", pCri.getPage());
		reAttr.addAttribute("numPerPage", pCri.getNumPerPage());
		
		reAttr.addFlashAttribute("result", "success");
		return "redirect:/bbs/pageList";
	}
		

	@RequestMapping(value="/delPage", method=RequestMethod.GET)
	public String delete(@RequestParam("bid") int bid, PageCriteria pCri, RedirectAttributes reAttr) throws Exception {
		
		bsvc.remove(bid);
		reAttr.addAttribute("page",pCri.getPage());
		reAttr.addAttribute("numPerPage", pCri.getNumPerPage());
		
		reAttr.addFlashAttribute("result", "success");
		return "redirect:/bbs/pageList";
	}
	
	//addAttribute�� Ű���� ������� �ʾ��� ��� key�� Ŭ������ �̸��� �ڵ����� �ҹ��ڷ� �����ؼ� ����Ѵ�.
	//read�޼����� ���ϰ� BbsVO�� Ŭ������ bbsVO�� key�� �ȴ�.
	
	//@RequestParam : Servlet���� request.getParameter()�� ������ ����̴�.
	//Servlet�� request�� HttpServletRequest
	//@RequestParam�� HttpServletRequest�� ������ : Servlet�� request�� HttpServletRequest�� ���ڿ�, ����, ��¥ ���� ����ȯ ����
	//@RequestMapping(value="/delete", method=RequestMethod.POST)
	//public String delete(@RequestParam("bid") int bid, RedirectAttributes reAttr) throws Exception {
	//	
	//	bsvc.remove(bid);
	//	reAttr.addFlashAttribute("result", "success");
	//	return "redirect:/bbs/list";
	//}
	
	
	//������ȸ
//	@RequestMapping(value="/modify", method=RequestMethod.GET)
//	public void modifyGET(int bid, Model model) throws Exception {
//		model.addAttribute(bsvc.read(bid));
//	}
	
	//����ó��
//	@RequestMapping(value="/modify", method=RequestMethod.POST)
//	public String modifyPOST(BbsVO bvo, RedirectAttributes reAttr) throws Exception {
//		logger.info("modifyPOST().........");
//		bsvc.modfiy(bvo);
//		
//		reAttr.addFlashAttribute("result", "success");
//		return "redirect:/bbs/list";
//	}
	
	
	//����¡ó���� GET���
	//���������� �ڵ����� �޼����� �Ķ������ Ŭ���� �����ڸ� �ڵ����� �����Ͽ� �����Ѵ�. 
	//�� PageCriteriaŬ������ �����ڸ� ���� page=1, numPerPage=10���� �����ڿ� ���� �����ؼ� ���������� ���� �ڵ����� ����Ǵ°�
	@RequestMapping(value="/PageListTest", method=RequestMethod.GET)
	public void pageListTest(PageCriteria pCria, Model model) throws Exception {
		logger.info("pageListTest()...............");
		model.addAttribute("list", bsvc.listCriteria(pCria));
	}
	
	@RequestMapping(value="/pageList", method=RequestMethod.GET)
	public void pageList(PageCriteria pCria, Model model) throws Exception {
		logger.info(pCria.toString());
		model.addAttribute("list", bsvc.listCriteria(pCria));
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCria);
//		pagingMaker.setTotalData(155);
		pagingMaker.setTotalData(bsvc.listCountData(pCria));
		model.addAttribute("pagingMaker", pagingMaker);

		model.addAttribute("finalEndPage", pagingMaker.getFinalEndPage());
	}
	
}
