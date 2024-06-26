package edu.pnu.dao;

import java.sql.PreparedStatement;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.LogVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LogDao {
	
	private final DataSource dataSource;
	
	public void addLog(String method, String sqlstring, boolean b) {
		addLog(LogVO.builder()
					.method(method)
					.sqlstring(sqlstring)
					.success(b)
					.build()
				);
	}
		
	public void addLog(LogVO logVO) {
		PreparedStatement psmt = null;
		
		try {
			psmt = dataSource.getConnection().prepareStatement("INSERT INTO DBLOG(method, sqlstring, success) values(?, ?, ?)");
			psmt.setString(1, logVO.getMethod());
			psmt.setString(2, logVO.getSqlstring());
			psmt.setBoolean(3, logVO.isSuccess()); //boolean type의 getter는 is로 만들어줌
			psmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(psmt != null) psmt.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	} 
}
