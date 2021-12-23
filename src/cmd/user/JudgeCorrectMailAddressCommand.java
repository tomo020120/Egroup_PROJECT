package cmd.user;

import java.util.List;

import cmd.AbstractCommand;
import cmd.RandomNumberGenerator.CreateRandomNumber;
import cmd.mail.SendMail;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.UserAccountInfoEditDao;
import dbManager.ConnectionManager;

public class JudgeCorrectMailAddressCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String newMailAddress = reqContext.getParameter("newUserMailAddress")[0];

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserAccountInfoEditDao edit = factory.getUserAccountInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		List<String> mailAddressList = edit.getAllUserMailAddress();

		if(mailAddressList.contains(newMailAddress)) { // 既に登録されていた場合はエラーメッセージを返す。
			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

			resContext.setMessage("このメールアドレスは登録されているため使用できません。");
			resContext.setTargetPath("userMailAddressEditForm");
			return resContext;
		}
		ConnectionManager.getInstance().closeTransaction();

		String sixDegits = CreateRandomNumber.getSixDegitsNumber(); // ６桁の乱数生成を行いメール転送。同時にセッションに格納する。

		reqContext.setSessionAttribute("authenticationCode", sixDegits);
		reqContext.setSessionAttribute("newMailAddress", newMailAddress);

		String messageBody = "認証コード:" + sixDegits;
		
		SendMail.send(newMailAddress,messageBody,"メールアドレス変更用認証コードのご案内");

		resContext.setTargetPath("authenticationPage");

		return resContext;
	}
}
