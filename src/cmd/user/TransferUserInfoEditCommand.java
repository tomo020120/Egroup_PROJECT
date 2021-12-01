package cmd.user;

import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.UserInfoEditDao;
import dbManager.ConnectionManager;

public class TransferUserInfoEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {

		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserInfoEditDao edit = factory.getEditUserInfoDao();

		UserBean userBean = (UserBean)reqContext.getToken();

		ConnectionManager.getInstance().beginTransaction();

		resContext.setResult(edit.getTargetUserInfo(userBean.getUserId()));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resContext.setTargetPath("userInfoEdit");
		return resContext;
	}

}