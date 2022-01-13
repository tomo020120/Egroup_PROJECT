package cmd.cart;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class DeleteCartProductsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		CartDao cartDao = factory.getCartDao();
		String itemId=reqc.getParameter("itemId")[0];
		//String orderCount=reqc.getParameter("orderCount")[0];
		//String price=reqc.getParameter("price")[0];

		//int subTotal = (Integer.parseInt(price) * Integer.parseInt(orderCount)); // 小計計算

		UserAndCartBean user=(UserAndCartBean)reqc.getToken();


    	// cartIdをセッションから取得
    	String cartId = user.getCartId();

		ConnectionManager.getInstance().beginTransaction();

		if(cartDao.deleteCartProduct(itemId, cartId)) {
			System.out.println("cartinside削除");
			if(cartDao.updateCartTotal(cartId)) {

				ConnectionManager.getInstance().commit();
				ConnectionManager.getInstance().closeTransaction();

				resc.setTargetPath("addCartComplete");
				System.out.println("cart追加");
				return resc;
			}
		}
		ConnectionManager.getInstance().rollback();
		ConnectionManager.getInstance().closeTransaction();

		System.out.println("cartinside失敗");
		throw new IntegrationException(null, null);
    }
}


