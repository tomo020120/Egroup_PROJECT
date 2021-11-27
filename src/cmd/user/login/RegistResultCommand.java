package cmd.user.login;

import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.login.RegistUserInfoDao;
import ex.IntegrationException;

public class RegistResultCommand extends AbstractCommand{

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		RegistUserInfoDao regist = factory.getRegistUserInfoDao();

		String UUID = reqContext.getParameter("UUID")[0];

		System.out.println("取得UUID :" + UUID);

		UserBean userBean = regist.getTempUserInfo(UUID);


		System.out.println(userBean.getMailAddress());
		if(regist.addUserInfo(userBean)) {
			resContext.setTargetPath("registComplete");
		}
		else {
			throw new IntegrationException(null,null);
		}

		return resContext;
	}

}
