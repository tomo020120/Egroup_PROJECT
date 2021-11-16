package dao;

import dao.user.login.LoginDao;
import dao.user.login.MySQLLoginDao;

public class MySQLDaoFactory extends AbstractDaoFactory{
    public ProductsDao getProductsDao(){
        return new MySQLProductsDao();
    }

    public LoginDao getLoginDao() {
    	return new MySQLLoginDao();
    }
}