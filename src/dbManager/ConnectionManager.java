package dbManager;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

import ex.InstantiateExceptoin;

public abstract class ConnectionManager{
	protected static Connection cn = null;
	private static ConnectionManager manager = null;

	protected ConnectionManager() {}

	public static ConnectionManager getInstance() {
		if(manager == null) {
			synchronized(ConnectionManager.class) {
				if(manager == null) {
					try {
						Properties prop = new Properties();
						prop.load(new FileInputStream("C:\\property\\dbManagers.properties"));
						String name = prop.getProperty("MySQL");
						Class<?> c = Class.forName(name);
						manager = (ConnectionManager)c.newInstance();
					}
					catch(Exception e) {
						e.printStackTrace();
						throw new InstantiateExceptoin(e.getMessage(),e);
					}
				}
			}
		}
		return manager;
	}

	public abstract Connection getConnection();
	public abstract void beginTransaction();
	public abstract void commit();
	public abstract void rollback();
	public abstract void closeTransaction();
}
