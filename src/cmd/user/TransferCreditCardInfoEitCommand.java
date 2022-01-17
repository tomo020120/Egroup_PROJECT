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

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		CreditCardInfoEditDao edit = factory.getCreditCardInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		resContext.setResult(edit.getCreditCardInfo(userId));
		resContext.setTargetPath("creditCardInfoEdit");

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		return resContext;
	}
}
