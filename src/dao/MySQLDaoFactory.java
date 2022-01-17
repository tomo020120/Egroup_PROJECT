package dao;

import dao.artists.ArtistsDao;
import dao.artists.MySQLArtistsDao;
import dao.cart.CartDao;
import dao.cart.MySQLCartDao;
import dao.favorite.FavoriteDao;
import dao.favorite.MySQLFavoriteDao;
import dao.products.MySQLProductsDao;
import dao.products.ProductsDao;
import dao.purchase.MySQLPurchaseDao;
import dao.purchase.PurchaseDao;
import dao.user.CreditCardInfoEditDao;
import dao.user.DeliveryInfoEditDao;
import dao.user.MySQLCreditCardInfoEditDao;
import dao.user.MySQLDeliveryInfoEditDao;
import dao.user.MySQLUserAccountInfoEditDao;
import dao.user.MySQLUserInfoEditDao;
import dao.user.UserAccountInfoEditDao;
import dao.user.UserInfoEditDao;
import dao.user.login.MySQLRegistUserInfoDao;
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

    public UserInfoEditDao getEditUserInfoDao() {
    	return new MySQLUserInfoEditDao();
    }

    public RegistUserInfoDao getRegistUserInfoDao() {
    	return new MySQLRegistUserInfoDao();
    }

    public UserSelectDao getUserSelectDao() {
    	return new MySQLUserSelectDao();
    }

    public CartDao getCartDao() {
    	return new MySQLCartDao();
    }

    public UserAccountInfoEditDao getUserAccountInfoEditDao() {
    	return new MySQLUserAccountInfoEditDao();
    }

	@Override
	public DeliveryInfoEditDao getDeliveryInfoEditDao() {
		return new MySQLDeliveryInfoEditDao();
	}

	public CreditCardInfoEditDao getCreditCardInfoEditDao() {
		return new MySQLCreditCardInfoEditDao();
	}

	public  PurchaseDao getPurchaseDao() {
		return new MySQLPurchaseDao();
	}

	@Override
	public FavoriteDao getFavoriteDao() {
		return new MySQLFavoriteDao();
	}
}