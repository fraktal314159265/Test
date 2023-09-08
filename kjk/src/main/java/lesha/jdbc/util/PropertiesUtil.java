package lesha.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
	
	private static final Properties PROPERTIES = new Properties();
	
	static {
		loadProperties();
	}

	private PropertiesUtil() {
	}
	
	public static String getProperty(String propertyName) {
		return PROPERTIES.getProperty(propertyName);
	}

	private static void loadProperties() {
		try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
			PROPERTIES.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}	
}
