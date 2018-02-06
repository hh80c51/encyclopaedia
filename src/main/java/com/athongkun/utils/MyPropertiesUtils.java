package com.athongkun.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertiesUtils {

	public static String getProperty(String key) {

		Properties properties = new Properties();

		InputStream resourceAsStream = MyPropertiesUtils.class.getClassLoader()
				.getResourceAsStream("imagePath.properties");

		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String property = properties.getProperty(key);

		return property;

	}

}
