package lesha.jdbc.JDBC_ADVANCED;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lesha.jdbc.util.ConnectionManager;

public class TRANS {
	
	public static void main(String[] args) throws Exception {
		
		String sql = "DELETE FROM testEclipse WHERE id > ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = ConnectionManager.open();
			preparedStatement = connection.prepareStatement(sql);
			
			connection.setAutoCommit(false);
			
			preparedStatement.setLong(1, 4);
			int k = preparedStatement.executeUpdate();
			System.out.println(k);
			connection.commit();
		} catch (Exception e) {
			if(connection != null) {
				connection.rollback();
			}
			throw e;
			}
		}
	}

