<<<<<<< HEAD
package cmd.cart;

import bean.CartInsideBean;
import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;
import dbManager.ConnectionManager;

public class AddCartProductCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		CartDao dao = factory.getCartDao();
		String itemId=reqc.getParameter("itemId")[0];
		String orderCount=reqc.getParameter("orderCount")[0];
		//外部キーのcartIDとってくる
		String cartId="1";
		String subTotal = "0";
		String total="0";//subtotakを加える
		UserBean userbean = (UserBean)reqc.getToken();
		String userId=userbean.getUserId();

		//以下一文は一時的な採用
		userId="1";
		System.out.println(userId);

		CartInsideBean cartInsideBean = new CartInsideBean();
		cartInsideBean.setOrderCount(orderCount);
		cartInsideBean.setSubTotal(subTotal);

		ConnectionManager.getInstance().beginTransaction();

		if(dao.addCartProduct(itemId, orderCount, subTotal, cartId)) {
			System.out.println("cartinside追加");
			if(dao.updateCartTotal(total,userId)) {

				ConnectionManager.getInstance().commit();
				ConnectionManager.getInstance().closeTransaction();

				resc.setTargetPath("CartAddComplete");
				System.out.println("cart追加");
				return resc;
			}
		}
		ConnectionManager.getInstance().rollback();
		ConnectionManager.getInstance().closeTransaction();

		System.out.println("cartinside失敗");
		resc.setTargetPath("error/integrationError");
		return resc;
    }
=======
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
>>>>>>> branch 'master' of git@github.com:tomo020120/Egroup_PROJECT.git
}