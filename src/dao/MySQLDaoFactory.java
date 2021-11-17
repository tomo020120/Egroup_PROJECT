package dao;

import dao.user.login.MySQLTempRegistDao;
import dao.user.login.TempRegistDao;

public class MySQLDaoFactory extends AbstractDaoFactory{
    public ProductsDao getProductsDao(){
        return new MySQLProductsDao();
    }

    public TempRegistDao getTempRegistDao() {
    	return new MySQLTempRegistDao();
    }
}