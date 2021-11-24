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

		String name = reqContext.getParameter("name")[0];
		String pass = reqContext.getParameter("pass")[0];
		String mail = reqContext.getParameter("mail")[0];

		UserBean userBean = new UserBean();

		userBean.setUserName(name);
		userBean.setUserPassword(pass);
		userBean.setMailAddress(mail);

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		RegistUserInfoDao regist = factory.getRegistUserInfoDao();

		if(regist.addUserInfo(userBean)) {
			resContext.setTargetPath("topPage");
		}
		else {
			throw new IntegrationException(null,null);
		}

		return resContext;
	}

}
