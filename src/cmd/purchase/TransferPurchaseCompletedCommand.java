package cmd.purchase;

import bean.joinBean.AllCartBean;
import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import cmd.mail.SendMail;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.purchase.PurchaseDao;
import dbManager.ConnectionManager;

public class TransferPurchaseCompletedCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqContext = getRequestContext();




		String userId = ((UserAndCartBean)(reqContext.getToken())).getUserId();
		String cartId = ((UserAndCartBean)(reqContext.getToken())).getCartId();
		int total = ((UserAndCartBean)(reqContext.getToken())).getTotal();
		System.out.println(userId);
		String deliveryInfoId=reqContext.getSessionAttribute("address").toString();
		String cardId=reqContext.getSessionAttribute("creditCard").toString();




		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		PurchaseDao dao = factory.getPurchaseDao();
    	ConnectionManager.getInstance().beginTransaction();


    	AllCartBean all=dao.getInsideCart(cartId);

    	String ItemId=all.getItemId();
    	String OrderCount=all.getOrderCount();
    	String subTotal=all.getSubTotal();

    	if(dao.PurchaseCompleted(ItemId,OrderCount,subTotal,cartId,total,userId)) {
			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

			UserAndCartBean userAndCartBean = (UserAndCartBean)reqContext.getToken();
			String messageBody = "購入完了";
			SendMail.send(userAndCartBean.getMailAddress(),messageBody);
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();
		}
    	resc.setTargetPath("completed");




		return resc;
	}

}