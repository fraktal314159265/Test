package lesha.jdbc.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lesha.jdbc.util.ConnectionManager;

public class SELECT_OPER {
	
	public static void main(String[] args) throws SQLException {
		
		String sql = "SELECT * FROM testEclipse";
		try(Connection connection = ConnectionManager.open();
			Statement statement = connection.createStatement()) {
			
			ResultSet executeResult = statement.executeQuery(sql);
			while (executeResult.next()) {
				System.out.println(executeResult.getLong("id"));
				System.out.println(executeResult.getString("name"));
			}
		}
	}
}
