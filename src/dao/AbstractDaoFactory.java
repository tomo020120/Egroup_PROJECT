
package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import dao.products.ProductsDao;
import dao.user.login.TempRegistDao;

public abstract class AbstractDaoFactory{
	//private static final String DAO_PROPERTY_PATH = new File("..\\workspace\\Egroup_PROJECT\\src\\property\\daoProperties.properties").getAbsolutePath();
	private static final String DAO_PROPERTY_PATH = "c:\\property\\daoProperties.properties";
	public static AbstractDaoFactory getDaoFactory(){
        AbstractDaoFactory absDaoFactory = null;
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream(DAO_PROPERTY_PATH));
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

    public abstract ProductsDao getProductsDao(); // 商品テーブル用のDao生成

    public abstract TempRegistDao getTempRegistDao(); // 一時ユーザーテーブルのDao生成
}