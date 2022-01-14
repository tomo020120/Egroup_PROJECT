package cmd.user;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.UserAccountInfoEditDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class ExecuteDeleteAccountCommand extends AbstractCommand{
	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		UserAndCartBean user=(UserAndCartBean)reqContext.getToken();
		String userId = user.getUserId();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserAccountInfoEditDao edit = factory.getUserAccountInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		if(edit.deleteAccount(userId)) {
			reqContext.removeSessionAttribute("token");

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();


			resContext.setTargetCommandPath("topPage");
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException("アカウント退会エラー", null);
		}

		return resContext;
	}
}