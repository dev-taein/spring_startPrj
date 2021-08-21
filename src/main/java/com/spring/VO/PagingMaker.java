package com.spring.VO;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PagingMaker {
	private int totalData; //��ü ������ ����
	private int startPage; //����������� ���۹�ȣ
	private int endPage;  //����������� ����ȣ
	private boolean prev; //���� ��ư�� ��Ÿ���� �ο� ��
	private boolean next; //���� ��ư
	private int finalEndPage; //������ ������
	private int displayPageNum = 10; //�ϴܿ� ��Ÿ���� ���۹�ȣ, ����ȣ�� ��
	private PageCriteria cri;
	
	public void setCri(PageCriteria cri) {
		this.cri = cri;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
		getPagingData();
	}
	public int getFinalEndPage() {
		finalEndPage = (int)(Math.ceil(totalData / (double)cri.getNumPerPage()));
		return finalEndPage;
	}
	public void setFinalEndPage(int finalEndPage) {
		this.finalEndPage = finalEndPage;
	}
	//����������, �������� ���ϱ�
	private void getPagingData() {
		//Math.ceil�Լ��� double���̹Ƿ� �Ҽ������� �޾ƾ��Ѵ�.
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		finalEndPage = (int)(Math.ceil(totalData / (double)cri.getNumPerPage()));
		if(endPage > finalEndPage) {
			endPage = finalEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage*cri.getNumPerPage() >= totalData ? false : true;
	}//getPageingData()
	
	/*
	 * double����ȯ �ϴ� ���� : �Ҽ������� ����� ����
	 * i = 3/100
	 * system.out.println(i); ====> 0
	 * i = (int)3.0 / 100
	 * system.out.println(i); ====> 33
	 * 
	 * double i = 3.0 / 100.0
	 * system.out.println(i); ====> 0.03
	 * 
	 * double i = 3 / 100.0
	 * system.out.println(i); ====> 0.03
	 * 
	 * double i = 100/(double)3;
	 * system.out.println(i); ====> 33.33333333
	 * */
	
	
	//uri
	public String makeURI(int page) {
		UriComponents uriComponents = UriComponentsBuilder
				.newInstance()
				.queryParam("page", page)
				.queryParam("numPerPage", cri.getNumPerPage())
				.build();
		return uriComponents.toUriString();
	}
	
	
	public String makeFind(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("numPerPage", cri.getNumPerPage())
				.queryParam("findType", ((FindCriteria)cri).getFindType())
				.queryParam("keyword", ((FindCriteria)cri).getKeyword())
				.build();
		
		return uriComponents.toUriString();
	}
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalData() {
		return totalData;
	}

	public PageCriteria getCri() {
		return cri;
	}
	

	
}
