package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;

@SpringBootTest
public class QueryAnnotationTest {
	@Autowired
	private BoardReository boardRepo;
	
	@BeforeEach //테스트 메서드 실행 이전에 수행
	public void dataPrepare() {
		for(int i = 1; i<=200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목" + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
	}
	
	//@Test
	public void testQueryAnnotationTest1() {
		List<Board> boardList = boardRepo.queryAnnotationTest1("테스트 제목 10");
		
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	//@Test
	public void testQueryAnnotationTest2() {
		List<Board> boardList = boardRepo.queryAnnotationTest2("테스트 제목 10");
		
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	//@Test
	public void testQueryAnnotationTest3() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest3("테스트 제목 10");
		
		System.out.println("검색 결과");
		for(Object[] row : boardList) {
			System.out.println("--->" + Arrays.toString(row));
		}
	}
	
	@Test
	public void testQueryAnnotationTest4() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest3("테스트 제목 10");
		
		System.out.println("검색 결과");
		for(Object[] row : boardList) {
			System.out.println("--->" + Arrays.toString(row));
		}
	}
}
