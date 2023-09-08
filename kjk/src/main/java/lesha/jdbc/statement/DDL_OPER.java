package lesha.jdbc.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import lesha.jdbc.util.ConnectionManager;

public class DDL_OPER {

	public static void main(String[] args) throws SQLException {
		
		String sql = "CREATE TABLE testEclipse ("
				+ "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
				+ "name VARCHAR(50), "
				+ "surname VARCHAR(50), "
				+ "age BIGINT);";
		try(Connection connection = ConnectionManager.open();
			Statement statement = connection.createStatement()) {
			boolean executeResult = statement.execute(sql);
			System.out.println(executeResult);
		}
	}

}
