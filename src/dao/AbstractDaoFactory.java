package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractDaoFactory{
	private static final String DAO_PROPERTY_PATH = new File("src\\property\\daoProperties.properties").getAbsolutePath();

	public static AbstractDaoFactory getDaoFactory(String factoryName){
        AbstractDaoFactory absDaoFactory = null;
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream(DAO_PROPERTY_PATH));
            String name = prop.getProperty(factoryName);
            Class<?> c = Class.forName(name);
            absDaoFactory = (AbstractDaoFactory)c.newInstance();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return absDaoFactory;
    }

    //public abstract ProductsDao getProductsDao();
}