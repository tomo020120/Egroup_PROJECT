package cmd.user;

import bean.joinBean.UesrInfoBean;
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

		UesrInfoBean UIB = new UesrInfoBean();

		UIB.setUserName(newName);

		edit.updateUserInfo(UIB);

		return null;
	}

}
