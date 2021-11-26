package cmd.user;

import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.EditUserInfoDao;

public class UpdateUserInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		EditUserInfoDao edit = factory.getEditUserInfoDao();

		String newName = reqContext.getParameter("newName")[0];

		UserBean userBean = new UserBean();

		userBean.setUserName(newName);

		edit.updateUserInfo(userBean);

		return null;
	}

}
