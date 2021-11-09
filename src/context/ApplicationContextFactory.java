package context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class ApplicationContextFactory {
	private static final String APPLICATIONCONTEXT_PROPERTY_PATH = new File("src\\property\\applicationContextProperties.properties").getAbsolutePath();

	public static ApplicationContext getApplicationContext(String key) {
		ApplicationContext app = null;
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(APPLICATIONCONTEXT_PROPERTY_PATH));
			String name = prop.getProperty(key);
			Class<?> c = Class.forName(name); // 警告を外すためジェネリクスにワイルドカード使用
			app = (ApplicationContext)c.newInstance();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return app;
	}
}
