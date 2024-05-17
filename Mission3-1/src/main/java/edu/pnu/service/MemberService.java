package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	public MemberService() {
		System.out.println("MemberService 생성");
	}
	
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}
	
	public MemberVO getMemberById(Integer id) {
		return dao.getMemberById(id);
	}
	
	public MemberVO addMemberVO(MemberVO memberVO) {
		return dao.addMemberVO(memberVO);
	}
	
	public int updateMembers(MemberVO memberVO) {
		return dao.updateMembers(memberVO);
	}
	
	public int removeMember(Integer id) {
		return dao.removeMember(id);
	}
}
