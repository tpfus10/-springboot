package edu.com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.com.domain.MemberVO;

@RestController
public class MemberController {
	
	//클래스의 속성으로 Map<MemberVO> 타입의 참조변수를 선언
	public Map<String, MemberVO> map = new HashMap<>();
	
	//생성자에서 메모리를 할당하여 객체 10개를 생성해서 저장
	public MemberController() {
		System.out.println("MemberController");
		for(int i = 1; i <= 10; i++) {
			MemberVO member = MemberVO.builder()
					.id(i)
					.name("name" + i)
					.pass("pass" + i)
					.regidate(new Date())
					.build();
			map.put(String.valueOf(i), member);
		}
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getAllMember() {
		return ResponseEntity.ok(map);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/members")
	public MemberVO addMember(MemberVO memberVO) {
		if(map.containsKey(memberVO.getId()) == true) {
			return null;
		}
		memberVO.setRegidate(new Date());
		map.put(String.valueOf(memberVO.getId()).toString(), memberVO);
		return memberVO;
	}
}
