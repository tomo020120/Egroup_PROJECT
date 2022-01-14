package cmd.user.login;

import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.login.RegistUserInfoDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class RegistResultCommand extends AbstractCommand{

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		RegistUserInfoDao regist = factory.getRegistUserInfoDao();

		String UUID = reqContext.getParameter("UUID")[0];

		System.out.println("取得UUID :" + UUID);

		ConnectionManager.getInstance().beginTransaction();

		UserBean userBean = regist.getTempUserInfo(UUID);

		System.out.println("登録メアド :" + userBean.getMailAddress());
		if(regist.addUserInfo(userBean)) {
			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

			resContext.setTargetPath("registComplete");
		}
		else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException(null,null);
		}

		return resContext;
	}

}
