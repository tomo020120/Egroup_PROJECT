package dao;

import dao.artists.ArtistsDao;
import dao.artists.MySQLArtistsDao;
import dao.products.MySQLProductsDao;
import dao.products.ProductsDao;
import dao.user.EditUserInfoDao;
import dao.user.MySQLEditUserInfoDao;
import dao.user.login.MySQLTempRegistDao;
import dao.user.login.TempRegistDao;

public class MySQLDaoFactory extends AbstractDaoFactory{
    public ProductsDao getProductsDao(){
        return new MySQLProductsDao();
    }

    public TempRegistDao getTempRegistDao() {
    	return new MySQLTempRegistDao();
    }

    public ArtistsDao getArtistsDao() {
    	return new MySQLArtistsDao();
    }

    public EditUserInfoDao getEditUserInfoDao() {
    	return new MySQLEditUserInfoDao();
    }
}