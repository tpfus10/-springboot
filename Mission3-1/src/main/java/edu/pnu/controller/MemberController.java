package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

	//방법4: @RequiredArgsConstructor와 fianl 필드변수
	//@RequiredArgsConstructor

@RestController
public class MemberController {
	
	//방법1: 필드변수에 Autowired
	@Autowired //자동으로 객체를 만들어서 DI해줌
	/*fianl*/private MemberService memberservice;
	
	//방법2: 생성자에 파라미터로 설정
//	@Autowired
//	public MemberController(MemberService memberservice) throws SQLException {
//		this.memberservice = memberservice;
//		System.out.println("MemberController 생성");
//	}
	
	//방법3: 세터를 만들어서 Autowired
//	@Autowired
//	public MemberController() throws SQLException {
//		memberservice  = new MemberService();
//		memberservice.setMemberDao(new MemberDAO());
//	}
		
	@GetMapping({"/member/{id}", "/member"})
	public ResponseEntity<?> getMember(@PathVariable(required = false) Integer id) {
		if (id == null)
			return ResponseEntity
				.ok(memberservice.getAllMember());
		
		return ResponseEntity
				.ok(memberservice.getMemberById(id));
	}
	
	@PostMapping("/member")
	public MemberVO addMemberVO(MemberVO memberVO) {
		System.out.println(memberservice.addMemberVO(memberVO));
		return memberservice.addMemberVO(memberVO);
	}
	
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		return memberservice.updateMembers(memberVO);
	}
	
	@DeleteMapping("/member/{id}") 
	public int removeMember(@PathVariable Integer id) {
		if(id == null)
			return 0;
		return memberservice.removeMember(id);
	}
}
