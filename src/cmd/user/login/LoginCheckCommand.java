package cmd.user.login;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.login.UserSelectDao;
import ex.IntegrationException;

public class LoginCheckCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		boolean flag=false;

		RequestContext reqContext = getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserSelectDao userSelect = factory.getUserSelectDao();
		String mailAddress = reqContext.getParameter("mailAddress")[0];
		System.out.println("入力メアド:" + mailAddress);
		String password = reqContext.getParameter("passWord")[0];
		System.out.println("入力メアド:" + password);

		flag=userSelect.selectMailAndPass(mailAddress,password);

		if(flag==true) {
			resContext.setTargetPath("topPage");
		}else {
			throw new IntegrationException(null,null);
		}
		return resContext;
	}
}