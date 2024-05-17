package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberController {

	// 클래스의 속성으로 List<MemberVO> 타입의 참조변수를 선언
	public List<MemberVO> list = new ArrayList<>();

	// 생성자에서 메모리를 할당하여 객체 10개를 생성해서 저장
	public MemberController() {
		for (int i = 1; i <= 10; i++) {
			list.add(MemberVO.builder().id(i).name("name" + i).pass("pass" + i).regidate(new Date()).build());
		}
	}

	// 검색(Read - select): 방법1
	@GetMapping("/members")
	public List<MemberVO> getAllMember() {
		return list;
	}
	
//	 //검색(Read - select): 방법2
//	 @GetMapping("/members") 
//	 public ResponseEntity<?> getAllMember() { 
//		 return  ResponseEntity.ok(list); 
//		 }

	// 검색(Read - select)
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) { // 파라미터는 Integer와 같이 객체형으로 넣어주는 게 좋음
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}

	// 입력(Create - insert) -> PostMan과 함께 써야 함
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		if (getMemberById(memberVO.getId()) != null)
			return null;
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}

//	//입력(Create - insert) 
//	@PostMapping("/memberJSON")
//	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
//		if(getMemberById(memberVO.getId()) != null)
//			return null;
//		memberVO.setRegidate(new Date());
//		list.add(memberVO);
//		return memberVO;
//	}

	@PostMapping("/memberJSON")
	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
		return addMember(memberVO);
	}

	// 수정(Update - update)
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if (m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}

	// 삭제(Delete - delete)
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		try {
			list.remove(getMemberById(id));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
