package cmd.user;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.UserAccountInfoEditDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class UpdateUserNameCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String newUserName = reqContext.getParameter("newUserName")[0];

		UserAndCartBean userAndCartBean = (UserAndCartBean) reqContext.getToken();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserAccountInfoEditDao edit = factory.getUserAccountInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		if(edit.updateUserName(newUserName,userAndCartBean.getUserId())) { // 更新出来たら、セッションの情報を書き換える
			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

			userAndCartBean.setUserName(newUserName);
			reqContext.setToken(userAndCartBean);

			resContext.setTargetCommandPath("accountInfoEdit");
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException("ユーザー名更新エラー", null);
		}

		return resContext;
	}

}
