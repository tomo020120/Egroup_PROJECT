package cmd.user;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import cmd.mail.SendMail;
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
			String messageBody = "お客様のリクエストに基づき、ユーザー名を変更いたしました。";
			SendMail.send(userAndCartBean.getMailAddress(),messageBody,"Ibanezアカウントの変更");

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

			userAndCartBean.setUserName(newUserName);

			reqContext.setToken(userAndCartBean);
			reqContext.setSessionAttribute("completeMessage", "お客様のアカウント情報は無事更新されました。");

			resContext.setTargetCommandPath("accountInfoEdit");
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException("ユーザー名更新エラー", null);
		}

		return resContext;
	}

}
