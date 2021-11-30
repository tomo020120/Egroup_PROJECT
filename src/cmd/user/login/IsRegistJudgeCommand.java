package cmd.user.login;

import java.util.List;

import bean.TemporaryUserBean;
import cmd.AbstractCommand;
import cmd.UUID.UUIDCreator;
import cmd.mail.SendMail;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.login.TempRegistDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class IsRegistJudgeCommand extends AbstractCommand {
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		TempRegistDao tempRegist = factory.getTempRegistDao();

		String mailAddress = reqContext.getParameter("mail")[0];

		System.out.println("入力メアド:" + mailAddress);

		ConnectionManager.getInstance().beginTransaction();

		List<String> mailAddressList = tempRegist.getUserMailAddress();

		if(mailAddressList.contains(mailAddress)) { // メールアドレスが既に存在してれば処理をやめてエラーメッセージ格納
			System.out.println("すでに登録されてた！");
			resContext.setMessage("そのメールアドレスは既に使用されているため登録できません。");
			resContext.setTargetPath("registUserInfo");

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

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
			SendMail.send(mailAddress,"http://localhost:9090/Egroup_PROJECT/registResult?UUID=" + tempUserBean.getUUID(),"新規会員登録");
			resContext.setTargetPath("sendComplete");
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException(null,null);
		}

		return resContext;
	}
}
