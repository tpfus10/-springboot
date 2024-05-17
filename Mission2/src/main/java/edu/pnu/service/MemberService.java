package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberService {

	public List<MemberVO> list = new ArrayList<>();
	
	//생성자에서 메모리를 할당하여 객체 10개를 저장
	public MemberService() {
		for (int i = 1; i<= 10; i++) {
			list.add(MemberVO
					.builder()
					.id(i)
					.name("name" + i)
					.pass("pass" + i)
					.regidate(new Date())
					.build());
			}
		}
	
	//검색(Read) *ResponseEntity는 Controller에만 씀
	public List<MemberVO> getAllmember() {
		return list;  
	}
	
	//검색(Select)
	public MemberVO getMemberById(Integer id) {
		for (MemberVO m : list) {
			if(m.getId() == id)
				return m;
		}
		return null;
	}
	
	//입력(Create - insert)
	public MemberVO addMemberVO(MemberVO memberVO) {
		if(getMemberById(memberVO.getId()) != null)
			return null;
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}
	
	//수정(Update)
	public int updateMembers (MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if (m == null) 
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	
	//삭제(Delete)
	public int removeMember(Integer id) {
		try {
			list.remove(getMemberById(id));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
