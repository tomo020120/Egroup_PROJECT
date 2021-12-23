package cmd.user;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.UserAccountInfoEditDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class UpdateUserMailAddressCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String inputAuthenticationCode = reqContext.getParameter("authenticaionCode")[0];
		String correctAuthenticationCode = (String)reqContext.getSessionAttribute("authenticationCode");

		System.out.println("入力されたコード: " + inputAuthenticationCode);
		System.out.println("正しいコード: " + correctAuthenticationCode);

		if(inputAuthenticationCode.equals(correctAuthenticationCode)) { // 認証コードが正しければ更新処理。そうでなければエラーメッセージ
			AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
			UserAccountInfoEditDao edit = factory.getUserAccountInfoEditDao();

			String newUserMailAddress = (String)reqContext.getSessionAttribute("newMailAddress");
			UserAndCartBean userAndCartBean = (UserAndCartBean)reqContext.getToken();

			ConnectionManager.getInstance().beginTransaction();
			if(edit.updateUserMailAddress(newUserMailAddress, userAndCartBean.getUserId())) { // 更新処理実行。
				ConnectionManager.getInstance().commit();
				ConnectionManager.getInstance().closeTransaction();

				userAndCartBean.setMailAddress(newUserMailAddress);

				reqContext.setToken(userAndCartBean);
				reqContext.removeSessionAttribute("newUserMailAddress"); // 不要のため消去
				reqContext.removeSessionAttribute("authenticationCode");

				resContext.setTargetCommandPath("accountInfoEdit");

				return resContext;
			}else {
				ConnectionManager.getInstance().rollback();
				ConnectionManager.getInstance().closeTransaction();

				throw new IntegrationException("メールアドレス更新エラー", null);
			}
		}else {
			resContext.setMessage("そのコードは正しくありません");
			resContext.setTargetPath("authenticationPage");

			return resContext;
		}
	}

}
