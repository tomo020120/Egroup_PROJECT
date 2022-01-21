package cmd.purchase;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.DeliveryInfoEditDao;
import dbManager.ConnectionManager;

public class TransferPurchraseAddressCommand extends AbstractCommand{

		public ResponseContext execute(ResponseContext resc) {
			RequestContext reqContext = getRequestContext();
	    	AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();


	    	String userId = ((UserAndCartBean)reqContext.getToken()).getUserId();


	    	DeliveryInfoEditDao edit = factory.getDeliveryInfoEditDao();

	    	ConnectionManager.getInstance().beginTransaction();

	    	resc.setResult(edit.getDeliveryInfo(userId));

	    	Object flag = reqContext.getSessionAttribute("errorFlag");
	    	if(flag != null) {
	    		boolean errorFlag = (boolean)flag;
	    		if(errorFlag) {
	    			resc.setMessage("入力された住所は登録済みです。");
	    			reqContext.removeSessionAttribute("errorFlag");
	    		}

	    	}
			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

			resc.setTargetPath("purchase");
			return resc;
		}

}



