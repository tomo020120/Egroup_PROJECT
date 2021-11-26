package dao;

import dao.artists.ArtistsDao;
import dao.artists.MySQLArtistsDao;
import dao.products.MySQLProductsDao;
import dao.products.ProductsDao;
<<<<<<< HEAD
import dao.user.EditUserInfoDao;
import dao.user.MySQLEditUserInfoDao;
=======
import dao.user.login.MySQLRegistUserInfoDao;
>>>>>>> branch 'master' of git@github.com:tomo020120/Egroup_PROJECT.git
import dao.user.login.MySQLTempRegistDao;
import dao.user.login.MySQLUserSelectDao;
import dao.user.login.RegistUserInfoDao;
import dao.user.login.TempRegistDao;
import dao.user.login.UserSelectDao;

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

<<<<<<< HEAD
    public EditUserInfoDao getEditUserInfoDao() {
    	return new MySQLEditUserInfoDao();
=======
    public RegistUserInfoDao getRegistUserInfoDao() {
    	return new MySQLRegistUserInfoDao();
    }

    public UserSelectDao getUserSelectDao() {
    	return new MySQLUserSelectDao();
>>>>>>> branch 'master' of git@github.com:tomo020120/Egroup_PROJECT.git
    }
}