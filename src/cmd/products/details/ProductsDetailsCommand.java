package cmd.products.details;

import java.util.ArrayList;
import java.util.List;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.favorite.FavoriteDao;
import dao.history.HistoryDao;
import dao.products.ProductsDao;
import dbManager.ConnectionManager;


public class ProductsDetailsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();

		ProductsDao productsDao = factory.getProductsDao();
		HistoryDao historyDao = factory.getHistoryDao();
		FavoriteDao favoriteDao = factory.getFavoriteDao();

		String itemId=reqc.getParameter("itemId")[0];
		String userId=null;

		if(reqc.getToken()!=null) {
			userId = ((UserAndCartBean)(reqc.getToken())).getUserId();
		}

		ConnectionManager.getInstance().beginTransaction();

		boolean favoriteFlag = false;

		if(userId!=null) {
			historyDao.addProductHistory(userId,itemId);
			favoriteFlag = favoriteDao.isAddFavoriteItem(itemId,userId);
		}

		List<Object> list = new ArrayList<Object>(); // 結果を二つ格納するためなんでも入るコレクションAPI作成

		list.add(productsDao.getProductsDetails(itemId));
		list.add(favoriteFlag);

		resc.setResult(list);

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("productsDetails");
		return resc;
    }
}