package cmd.user.login;

import java.util.List;

import bean.TemporaryUserBean;
import cmd.AbstractCommand;
import cmd.UUID.UUIDCreator;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.login.TempRegistDao;

public class IsRegistJudgeCommand extends AbstractCommand {
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		TempRegistDao tempRegist = factory.getTempRegistDao();

		String mailAddress = reqContext.getParameter("mail")[0];

		List<String> mailAddressList = tempRegist.getUserMailAddress();

		if(mailAddressList.contains(mailAddress)) { // メールアドレスが既に存在してれば処理をやめてエラーメッセージ格納
			resContext.setResult2("そのメールアドレスは既に使用されているため登録できません。");
			resContext.setTargetPath("regist");

			return resContext;
		}

		String userName = reqContext.getParameter("name")[0];
		String userPass = reqContext.getParameter("pass")[0];

		TemporaryUserBean tempUserBean = new TemporaryUserBean();

		tempUserBean.setUserName(userName);
		tempUserBean.setMailAddress(mailAddress);
		tempUserBean.setUserPassword(userPass);

		tempUserBean.setUUID(UUIDCreator.getUUID());

		if(tempRegist.addTempUserLoginInfo(tempUserBean)) {

		}

		return resContext;
	}
}
