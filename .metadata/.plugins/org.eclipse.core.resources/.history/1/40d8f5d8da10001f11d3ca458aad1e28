package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MemberController {
	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void test() {
		List<MemberVO> list = memberDAO.getAllMember();
		
		for(MemberVO m : list)
			System.out.println(m);
	}
	
	
}
