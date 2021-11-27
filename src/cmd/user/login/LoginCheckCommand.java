package cmd.user.login;

import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.login.UserSelectDao;

public class LoginCheckCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserSelectDao userSelect = factory.getUserSelectDao();

		String mailAddress = reqContext.getParameter("mailAddress")[0];
		System.out.println("入力メアド:" + mailAddress);
		String password = reqContext.getParameter("passWord")[0];
		System.out.println("入力メアド:" + password);

		UserBean userBean = userSelect.getUserInfo(mailAddress,password);

		if(userBean != null) { // 認証できてればセッションに登録し、ログインする前にいたところに飛ばす。認証できなければログインページにメッセージを返す。
			reqContext.setToken(userBean);
			resContext.setTargetPath(reqContext.getPastLocation());
		}else {
			resContext.setMessage("メールアドレスまたはパスワードが間違っています。");
			resContext.setTargetPath("login");
		}

		return resContext;
	}
}