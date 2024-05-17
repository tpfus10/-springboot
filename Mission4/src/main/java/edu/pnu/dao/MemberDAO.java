package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDAO {
	private Connection con;
	
	public MemberDAO() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission3", "sa", "abcd");
		}
		catch (Exception e) {
			System.out.println("DB 연결 실패");
		}
	}
	
	//검색(Read)
		public Map<String, Object> getAllMember () { //여러 타입의 데이터를 받을 수 있도록 Map을 사용 
			Statement stmt = null;
			ResultSet rs = null;
			boolean flag = true;
			
			List<MemberVO> list = new ArrayList<>();
			String sqlstring = "SELECT * FROM MEMBER";
			
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sqlstring);
				
				while(rs.next()) {
					list.add(MemberVO.builder()
									 .id(rs.getInt("id"))
									 .pass(rs.getString("pass"))
									 .name(rs.getString("name"))
									 .regidate(rs.getDate("regidate"))
									 .build());
				}
			}
			catch (Exception e) {
				System.out.println("멤버 조회 중 예외 발생");
				e.printStackTrace();
				flag = false;
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
			Map<String, Object> map = new HashMap<>();
			map.put("list", list);
			map.put("method", "Get");
			map.put("sqlstring", sqlstring);
			map.put("success", flag);
			return map;
		}
		
		//검색(Select)
		public Map<String, Object> getMemberById(Integer id) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			boolean flag = true;
			
			MemberVO m = new MemberVO();   
			String sqlstring = "SELECT id, pass, name, regidate FROM MEMBER WHERE id = ?";
			
			try {
				psmt = con.prepareStatement(sqlstring);
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
				System.out.println("멤버 조회 중 예외 발생");
				e.printStackTrace();
				flag = false;
			}
			finally {
				sqlstring = psmt.toString();
				int index = sqlstring.indexOf(":");
				sqlstring = sqlstring.substring(index+2);
				try {
					if(psmt != null) psmt.close();
					if(rs != null) rs.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			Map<String, Object> map = new HashMap<>();
			map.put("member", m);
			map.put("method", "Get");
			map.put("sqlstring", sqlstring);
			map.put("success", flag);
			return map;
		}
		
		//입력(Insert)
		public Map<String, Object> addMemberVO(MemberVO memberVO) {
			PreparedStatement psmt = null;
			boolean flag = true;
			
			MemberVO m = memberVO;
			String sqlstring = "INSERT INTO MEMBER(pass, name) values(?, ?)";
			
			try {
				psmt = con.prepareStatement(sqlstring);
				
				psmt.setString(1, m.getPass());
				psmt.setString(2, m.getName());
				psmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("멤버 데이터 입력 중 예외 발생");
				e.printStackTrace();
				flag = false;
			}
			finally {
				sqlstring = psmt.toString();
				int index = sqlstring.indexOf(":");
				sqlstring = sqlstring.substring(index+2);
					try {
						if(psmt != null) psmt.close();
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
			}
			Map<String, Object> map = new HashMap<>();
			map.put("member", m);
			map.put("method", "Post");
			map.put("sqlstring", sqlstring);
			map.put("success", flag);
			return map;
		}		
		
		//수정(Update)
		public Map<String, Object> updateMembers(MemberVO memberVO) {
			PreparedStatement psmt = null;
			int result;
			boolean flag = true;
			
			MemberVO m = (MemberVO) getMemberById(memberVO.getId()).get("member");
			String sqlstring = "UPDATE Member " 
							 + "SET name = COALESCE(?, name), "
							 + "pass = COALESCE(?, pass) " 
					         + "WHERE id = ?";
			
			if(m == null)
				return null;
			try {
				psmt = con.prepareStatement(sqlstring);
				
				psmt.setString(1, memberVO.getName());
				psmt.setString(2, memberVO.getPass());
				psmt.setInt(3, memberVO.getId());
				System.out.println(memberVO);
				result = psmt.executeUpdate();
			}
			catch (Exception e) {
				e.printStackTrace();
				result = 0;
				flag = false;
			}
			finally {
				sqlstring = psmt.toString();
				int index = sqlstring.indexOf(":");
				sqlstring = sqlstring.substring(index+2);
					try {
						if(psmt != null) psmt.close();
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
			}
			Map<String, Object> map = new HashMap<>();
			map.put("result", result);
			map.put("methodd", "Put");
			map.put("sqlstring", sqlstring);
			map.put("success", flag);
			
			return map;
		}
		
		//삭제(Delete)
		public Map<String, Object> removeMember(Integer id) {
			PreparedStatement psmt = null;
			String sqlstring = "DELETE FROM member WHERE id =?";
			int result;
			boolean flag = true;
			
			try {
			psmt = con.prepareStatement(sqlstring);
			psmt.setInt(1, id);
			result = psmt.executeUpdate();
			}
			catch (Exception e) {
				e.printStackTrace();
				result = 0;
				flag = false;
			}
			finally {
				sqlstring = psmt.toString();
				int index = sqlstring.indexOf(":");
				sqlstring = sqlstring.substring(index+2);
				try {
					if(psmt != null) psmt.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			Map<String, Object> map = new HashMap<>();
			map.put("result", result);
			map.put("method", "Delete");
			map.put("sqlstring", sqlstring);
			map.put("success", flag);
			return map;
		}
}
