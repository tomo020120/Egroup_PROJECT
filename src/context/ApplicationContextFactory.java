package context;

import java.io.IOException;
import java.util.Properties;

public abstract class ApplicationContextFactory {
	//private static final String APPLICATIONCONTEXT_PROPERTY_PATH = "c:\\property\\applicationContextProperties.properties";
//	private static final String APPLICATIONCONTEXT_PROPERTY_PATH = new File("..\\workspace\\Egroup_PROJECT\\src\\property\\applicationContextProperties.properties").getAbsolutePath();

	public static ApplicationContext getApplicationContext(String key) {
		ApplicationContext app = null;
		Properties prop = new Properties();

		try {
			//prop.load(new FileInputStream(APPLICATIONCONTEXT_PROPERTY_PATH));
			prop.load(ApplicationContextFactory.class.getClassLoader().getResourceAsStream("property/applicationContextProperties.properties"));


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
