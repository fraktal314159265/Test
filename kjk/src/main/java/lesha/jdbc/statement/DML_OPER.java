package lesha.jdbc.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import lesha.jdbc.util.ConnectionManager;

public class DML_OPER {

		public static void main(String[] args) throws SQLException {
			
			String sql = "INSERT INTO testEclipse (name, surname, age) VALUES"
					+ "('lesha', 'olaru', 25),"
					+ "('alina', 'abdulaeva', 24),"
					+ "('dacha', 'tarahka', 23),"
					+ "('ksuha', 'kulakova', 40);";
			try(Connection connection = ConnectionManager.open();
				Statement statement = connection.createStatement()) {
				int executeResult = statement.executeUpdate(sql);
				System.out.println(executeResult);
			}
		}
}
