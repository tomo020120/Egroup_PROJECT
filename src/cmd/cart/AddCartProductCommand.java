
package cmd.cart;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class AddCartProductCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		CartDao cartDao = factory.getCartDao();
		String itemId=reqc.getParameter("itemId")[0];
		String orderCount=reqc.getParameter("orderCount")[0];
		String price=reqc.getParameter("price")[0];

		int subTotal = (Integer.parseInt(price) * Integer.parseInt(orderCount)); // 小計計算

		UserAndCartBean user=(UserAndCartBean)reqc.getToken();

    	if(user==null) {
    		resc.setTargetCommandPath("login"); // 「TransferLoginCommand」へ転送
    		return resc;
    	}

    	// cartIdをセッションから取得
    	String cartId = user.getCartId();

		ConnectionManager.getInstance().beginTransaction();

		if(cartDao.addCartProduct(itemId, orderCount, subTotal, cartId)) {
			System.out.println("cartinside追加");
			if(cartDao.updateCartTotal(cartId)) {
				int totalAmount = cartDao.getTotalAmount(cartId);

				user.setTotal(totalAmount); // セッション内のカート合計金額を更新

				resc.setResult(cartDao.getPictPath(itemId,totalAmount));
				resc.setTargetPath("addCartComplete");

				ConnectionManager.getInstance().commit();
				ConnectionManager.getInstance().closeTransaction();
				return resc;
			}
		}
		ConnectionManager.getInstance().rollback();
		ConnectionManager.getInstance().closeTransaction();

		System.out.println("cartinside失敗");
		throw new IntegrationException(null, null);
    }

}