package cmd.user;

import java.util.List;

import bean.joinBean.UesrInfoBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.login.TempRegistDao;

public class UserInfoJudgeCommand extends AbstractCommand{

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		// TODO 自動生成されたメソッド・スタブ

		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		TempRegistDao tempRegist = factory.getTempRegistDao();

		String mailAddress = reqContext.getParameter("mail")[0];

		List<String> mailAddressList = tempRegist.getUserMailAddress();

		if(mailAddressList.contains(mailAddress)) { // メールアドレスが既に存在してれば処理をやめてエラーメッセージ格納
			resContext.setMessage("そのメールアドレスは既に使用されているため登録できません。");
			resContext.setTargetPath("regist");

			return resContext;
		}

		String userName = reqContext.getParameter("name")[0];
		String emailAddress = reqContext.getParameter("emailAddress")[0];
		String userPass = reqContext.getParameter("pass")[0];
		String cardCode = reqContext.getParameter("cardCode")[0];
		String address = reqContext.getParameter("address")[0];
		String tel = reqContext.getParameter("tel")[0];
		String postalCode = reqContext.getParameter("postalCode")[0];

		UesrInfoBean UIB = new UesrInfoBean();

		UIB.setUserName(userName);
		UIB.setEmailAddress(emailAddress);
		UIB.setUserPassword(userPass);
		UIB.setCartCode(cardCode);
		UIB.setAddress(address);
		UIB.setTel(tel);
		UIB.setPostalCode(postalCode);


		return resContext;
	}

}
