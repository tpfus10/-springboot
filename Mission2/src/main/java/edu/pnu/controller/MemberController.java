package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	MemberService memberservice;

	//memberservice 참조 변수 선언
	public MemberController() {
		memberservice = new MemberService();
	}
	
	//검색(Read)
	@GetMapping("/members")
	public ResponseEntity<?> getAllMember() {
		return ResponseEntity
				.ok(memberservice.getAllmember());
	}
	
	//검색(Select)
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		return memberservice.getMemberById(id);
	}
	
	//입력(Create - insert)
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		return memberservice.addMemberVO(memberVO);
	}
	
	//수정(Update)
	@PutMapping("/member")
	public int updateMembers (MemberVO memberVO) {
		return memberservice.updateMembers(memberVO);
	}
	
	//삭제(Delete)
	@DeleteMapping("/member")
	public int removeMeber(Integer id) {
		return memberservice.removeMember(id);
	}
}
