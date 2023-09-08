package lesha.jdbc.util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionManager {
	private static final String URL = "db.url";
	private static final String USERNAME = "db.username";
	private static final String PASSWORD = "db.password";
	private static final String POOL_SIZE = "db.pool.size";
	private static BlockingQueue<Connection> pool;
	private static List<Connection> connections;
	
	static {
		loadDriver();
		initConnectionPool();
	}
	
	private ConnectionManager() {
	}
	
	private static void initConnectionPool() {
		String poolSize = PropertiesUtil.getProperty(POOL_SIZE);
		pool = new ArrayBlockingQueue<>(Integer.parseInt(poolSize));
		connections = new ArrayList<>();
		for (int i = 0; i < Integer.parseInt(poolSize); i++) {
			Connection connection = getConnection();
			Connection proxyConnection = (Connection) Proxy.newProxyInstance(
					ConnectionManager.class.getClassLoader(), 
					new Class[] {Connection.class}, 
					(proxy, method,args) -> method.getName().equals("close") ? pool.add((Connection)proxy) 
							: method.invoke(connection, args));
			pool.add(proxyConnection);
			connections.add(connection);
		}
	}
	
	public static Connection open() {
		try {
			return pool.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					PropertiesUtil.getProperty(URL),
					PropertiesUtil.getProperty(USERNAME),
					PropertiesUtil.getProperty(PASSWORD));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void loadDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void closePool() {
		for (Connection connection : connections) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
