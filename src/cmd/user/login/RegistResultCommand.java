package cmd.user.login;

import bean.TemporaryUserBean;
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

		TemporaryUserBean tempUserBean = regist.getTempUserInfo(UUID);

		System.out.println("登録メアド :" + tempUserBean.getMailAddress());
		if(regist.addUserInfo(tempUserBean)) {
			reqContext.setToken(tempUserBean); // 登録後に即ログインをするためにセッションに登録しておく
			resContext.setTargetPath("registComplete");

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}
		else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException(null,null);
		}

		return resContext;
	}

}
