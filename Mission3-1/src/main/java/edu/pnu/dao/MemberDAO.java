package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDAO {
	private Connection con;
	
	//기본 생성자: DB 연결
	public MemberDAO() {
		System.out.println("MemberDAO 생성");
		try {
			String url = "jdbc:h2:tcp://localhost/~/.h2/mission3";
			String id = "sa";
			String pwd = "abcd";
			
			con = DriverManager.getConnection(url,id,pwd);
			System.out.println("DB 연결 성공");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//검색(Read)
		public List<MemberVO> getAllMember () {
			Statement stmt = null;
			ResultSet rs = null;
			List<MemberVO> list = new ArrayList<>();
			
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT * FROM MEMBER");
				
				while(rs.next()) {
					MemberVO m = MemberVO.builder()
										.id(rs.getInt("id"))
										.pass(rs.getString("pass"))
										.name(rs.getString("name"))
										.regidate(rs.getDate("regidate"))
										.build();
					list.add(m);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {  
					if(stmt != null) stmt.close();
					if(rs != null) rs.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		
		//검색(Select)
		public MemberVO getMemberById(Integer id) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			MemberVO m = new MemberVO();
			
			try {
				psmt = con.prepareStatement("SELECT id, pass, name, regidate FROM MEMBER WHERE id = ?");
				psmt.setInt(1, id);
				rs = psmt.executeQuery(); //executeQuery의 리턴타입은 resultset
				
				while(rs.next()) {
					m.setId(rs.getInt("id"));
					m.setPass(rs.getString("pass"));
					m.setName(rs.getString("name"));
					m.setRegidate(rs.getDate("regidate"));	
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(psmt != null) psmt.close();
					if(rs != null) rs.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return m;
		}
		
		//입력(Insert)
		public MemberVO addMemberVO(MemberVO memberVO) {
			PreparedStatement psmt = null;
			MemberVO m = memberVO;
			
			try {
				psmt = con.prepareStatement("INSERT INTO MEMBER(pass, name) values(?, ?)");
				
				psmt.setString(1, m.getPass());
				psmt.setString(2, m.getName());
				psmt.executeUpdate();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
					try {
						if(psmt != null) psmt.close();
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return m;
		}		
		
		//수정(Update)
		public int updateMembers(MemberVO memberVO) {
			PreparedStatement psmt = null;
			MemberVO m = getMemberById(memberVO.getId());
			if(m == null)
				return 0;
			try {
				psmt = con.prepareStatement("UPDATE member SET pass= ?, name= ? WHERE id=?");
				
				psmt.setString(1, memberVO.getPass());
				psmt.setString(2, memberVO.getName());
				psmt.setInt(3, memberVO.getId());
				System.out.println(memberVO);
				psmt.executeUpdate();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
					try {
						if(psmt != null) psmt.close();
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return 1;
		}
		
		//삭제(Delete)
		public int removeMember(Integer id) {
			PreparedStatement psmt = null;
			try {
			psmt = con.prepareStatement("DELETE FROM member WHERE id =?");
			psmt.setInt(1, id);
			psmt.executeUpdate();
			}
			catch (Exception e) {
				return 0;
			}
			finally {
					try {
						if(psmt != null) psmt.close();
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return 1;
		}
}
