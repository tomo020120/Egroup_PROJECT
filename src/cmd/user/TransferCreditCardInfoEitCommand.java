package cmd.user;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.CreditCardInfoEditDao;
import dbManager.ConnectionManager;

public class TransferCreditCardInfoEitCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		String userId = ((UserAndCartBean)(reqContext.getToken())).getUserId();

		String message = "";
		try {
			message = reqContext.getParameter("message")[0];
		}
		catch(NullPointerException e) {
			message = "";
		}

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		CreditCardInfoEditDao edit = factory.getCreditCardInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		resContext.setResult(edit.getCreditCardInfo(userId));
		resContext.setMessage(message);
		resContext.setTargetPath("creditCardInfoEdit");

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		return resContext;
	}
}