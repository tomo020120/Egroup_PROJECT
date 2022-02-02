package cmd.user.login;

import bean.TemporaryUserBean;
import bean.UserBean;
import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import cmd.RandomNumberGenerator.CreateRandomNumber;
import cmd.mail.SendMail;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;
import dao.user.login.UserSelectDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class LoginCheckCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserSelectDao userSelect = factory.getUserSelectDao();

		TemporaryUserBean tempUserBean = (TemporaryUserBean)reqContext.getToken(); // 新規登録時のトークンを取得

		String mailAddress = null;
		String password = null;

		if(tempUserBean != null) { // 新規登録の場合はフォーム入力値ではなくセッションから値を取得
			mailAddress = tempUserBean.getMailAddress();
			password = tempUserBean.getUserPassword();
		}else {
			mailAddress = reqContext.getParameter("mailAddress")[0];
			password = reqContext.getParameter("passWord")[0];
		}

		System.out.println("メアド:" + mailAddress);
		System.out.println("パスワード:" + password);

		if((mailAddress == "adm1n.ibanez0120@gmail.com") && (password == "adminpass0120")) { // もし管理者アカウントでのログインならワンタイムパスワードを生成し認証する。
			String sixDegits = CreateRandomNumber.getSixDegitsNumber();
			reqContext.setSessionAttribute("authenticationCode", sixDegits);

			SendMail.send("adm1n.ibanez0120@gmail.com","認証コード:" + sixDegits, "管理者ログイン用認証コード");

			resContext.setTargetPath("adminAuthenticationPage");

			return resContext;
		}


		ConnectionManager.getInstance().beginTransaction();

		UserBean userBean = userSelect.getUserInfo(mailAddress,password);

		if(userBean != null) { // 認証できてればセッションに登録し、ログインする前にいたところに飛ばす。認証できなければログインページにメッセージを返す。
			String userId = userBean.getUserId();

			CartDao cart = factory.getCartDao();

			if(!cart.isExistenceCart(userId)) { // もしカートがまだ存在してなければ新規カート生成
				System.out.println("カートがまだ存在してないので新規作成");
				if(!cart.createCart(userId)) {
					ConnectionManager.getInstance().rollback();
					ConnectionManager.getInstance().closeTransaction();

					throw new IntegrationException(null, null);
				}
			}

			UserAndCartBean userAndCartBean = userSelect.getUserAndCartInfo(userId);

			reqContext.setToken(userAndCartBean);

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

			resContext.setTargetCommandPath(reqContext.getPastLocation());
			System.out.println("転送先 : " + resContext.getTargetPath());
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			resContext.setMessage("メールアドレスまたはパスワードが間違っています。");
			resContext.setTargetPath("login");
		}

		return resContext;
	}
}