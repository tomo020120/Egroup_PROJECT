package cmd.user;

import bean.joinBean.UesrInfoBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.EditUserInfoDao;
import dbManager.ConnectionManager;

public class UpdateUserInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		EditUserInfoDao edit = factory.getEditUserInfoDao();

		String newName = reqContext.getParameter("newName")[0];

		UesrInfoBean UIB = new UesrInfoBean();

		UIB.setUserName(newName);

		ConnectionManager.getInstance().beginTransaction();

		edit.updateUserInfo(UIB);

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		return null;
	}

}
