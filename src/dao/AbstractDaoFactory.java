
package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractDaoFactory{
	public static AbstractDaoFactory getDaoFactory(){
        AbstractDaoFactory absDaoFactory = null;
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream("C:\\property\\daoProperties.properties"));
            String name = prop.getProperty("MySQLDao");
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

    public abstract ProductsDao getProductsDao();
}