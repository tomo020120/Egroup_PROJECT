package cmd.user;

import bean.joinBean.AllUserInfoBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.UserInfoEditDao;
import dbManager.ConnectionManager;

public class UpdateUserInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserInfoEditDao edit = factory.getEditUserInfoDao();

		String newName = reqContext.getParameter("newName")[0];

		AllUserInfoBean UIB = new AllUserInfoBean();

		UIB.setUserName(newName);

		ConnectionManager.getInstance().beginTransaction();

		edit.updateUserInfo(UIB);

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		return null;
	}

}
