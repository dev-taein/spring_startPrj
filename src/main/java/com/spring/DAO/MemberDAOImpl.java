package com.spring.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.VO.MemberVO;

//DAO�� ���� ��ü
//@Service�ؿ� �ִ� ���� Repository�� DAO�� �������� �νĽ�Ű�� ���� ������̼��̴�.
@Repository
public class MemberDAOImpl implements MemberDAO{
	
	//memberMapper.xml�� namespace�� ����� �̿��� ���
	private static final String namespace = "com.spring.MemberMapper";
	
	//���� ����-- Autowired��� Inject�� ���
	@Inject
	private SqlSession sqlSession;
	
	
	@Override
	public String getTime() {
		//selectOne(Mapper.xml�� id��)
		return sqlSession.selectOne("getTime");
		
		//memberMapper.xml�� namespace�� �̿��� ���
//		return sqlSession.selectOne(namespace+".getTime");
	}
	
	
	@Override
	public void inserMember(MemberVO mvo) {
		sqlSession.insert("insertMember", mvo);
		
		//memberMapper.xml�� namespace�� ����� �̿��� ���
//		sqlSession.insert(namespace+".insertMember",mvo);
	}
}
