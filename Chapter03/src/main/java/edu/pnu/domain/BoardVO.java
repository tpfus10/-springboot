package edu.pnu.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter //자동으로 게터를 생성해줌
@Setter //자동으로 세터를 생성해줌
@ToString
@AllArgsConstructor //모든 필드를 가지는 생성자
@NoArgsConstructor //기본 생성자
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date createDate = new Date();
	private int cnt = 0;
}
