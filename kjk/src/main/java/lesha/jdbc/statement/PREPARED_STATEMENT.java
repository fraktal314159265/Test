package lesha.jdbc.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lesha.jdbc.util.ConnectionManager;

public class PREPARED_STATEMENT {

	public static void main(String[] args) throws SQLException {
		
		String sql = "SELECT * FROM testEclipse "
				+ "WHERE id = ?;";
		try(Connection connection = ConnectionManager.open();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, 5L);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getLong("id"));
				System.out.println(resultSet.getString("name"));
				System.out.println(resultSet.getString("surname"));
				System.out.println(resultSet.getLong("age"));
			}
		}
	}

}
