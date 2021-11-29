package cmd.user.login;

import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;
import dao.user.login.UserSelectDao;
import ex.IntegrationException;

public class LoginCheckCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserSelectDao userSelect = factory.getUserSelectDao();

		String mailAddress = reqContext.getParameter("mailAddress")[0];
		System.out.println("入力メアド:" + mailAddress);
		String password = reqContext.getParameter("passWord")[0];
		System.out.println("入力パスワード:" + password);

		UserBean userBean = userSelect.getUserInfo(mailAddress,password);

		if(userBean != null) { // 認証できてればセッションに登録し、ログインする前にいたところに飛ばす。認証できなければログインページにメッセージを返す。
			reqContext.setToken(userBean);

			CartDao cart = factory.getCartDao();

			if(!cart.createCart(userBean.getUserId())) { // ログインと同時にカートの生成
				throw new IntegrationException(null, null);
			}

			resContext.setTargetCommandPath(reqContext.getPastLocation());
			System.out.println("転送先 : " + resContext.getTargetPath());
		}else {
			resContext.setMessage("メールアドレスまたはパスワードが間違っています。");
			resContext.setTargetPath("login");
		}

		return resContext;
	}
}