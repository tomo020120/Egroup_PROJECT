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

public class UpdatePasswordCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String newPassword = reqContext.getParameter("newPassword")[0];

		UserAndCartBean userAndCartBean = (UserAndCartBean)reqContext.getToken();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserAccountInfoEditDao edit = factory.getUserAccountInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();
		if(edit.updatePassword(newPassword, userAndCartBean.getUserId())) {


			String messageBody = "お客様のリクエストに基づき、パスワードを変更いたしました。";
			SendMail.send(userAndCartBean.getMailAddress(),messageBody,"Ibanezアカウントの変更");

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

			userAndCartBean.setUserPassword(newPassword);

			reqContext.setToken(userAndCartBean);

			reqContext.setSessionAttribute("completeMessage", "お客様のアカウント情報は無事更新されました。");
			resContext.setTargetCommandPath("accountInfoEdit");
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException("パスワード更新エラー", null);
		}

		return resContext;
	}

}
