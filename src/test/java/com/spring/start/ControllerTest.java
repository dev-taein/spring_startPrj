package com.spring.start;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:/src/main/webapp/WEB-INF/spring/**/*xml"})
public class ControllerTest {
	
	//��Ĺ���� ��Ʈ�ѷ� �׽�Ʈ
	private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);
	
	
	@Inject
	private WebApplicationContext webAppCtx;
	
	//���������� ��û�� ������ �ϴ� ��ü�� �ǹ�
	private MockMvc mockMvc;
	
	//�׽�Ʈ�ϱ����� �� �޼ҵ带 ���� �����Ѵٴ� �ǹ�
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppCtx).build();
		logger.info("setup()ȣ��......");
	}
	
	@Test
	public void testController() throws Exception {
		//������ �������� ��û
		//MockMvcRequestBuilders.get�� get���,,, MockMvcRequestBuilders.post�� post���
		mockMvc.perform(MockMvcRequestBuilders.get("/controller")); 
	}
}
