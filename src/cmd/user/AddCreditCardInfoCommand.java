package cmd.user;

import bean.CreditCardBean;
import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import cmd.authentication.CreditCardSecurityCodeAuthentication;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.CreditCardInfoEditDao;
import dbManager.ConnectionManager;

public class AddCreditCardInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String cardOwnerName = reqContext.getParameter("cardOwnerName")[0];
		String cardNo = reqContext.getParameter("creditCardNo")[0];
		String cardCompany = reqContext.getParameter("cardCompany")[0];
		String expirationMonth = reqContext.getParameter("month")[0];
		String expirationYear = reqContext.getParameter("year")[0];
		String securityCode = reqContext.getParameter("securityCode")[0];

		String userId = ((UserAndCartBean)reqContext.getToken()).getUserId();

		String expirationDate = expirationYear + "/" + expirationMonth; // 有効期限

		CreditCardBean creditCardBean =new CreditCardBean();

		creditCardBean.setCardOwnerName(cardOwnerName);
		creditCardBean.setCardNo(cardNo);
		creditCardBean.setCardCompany(cardCompany);
		creditCardBean.setExpirationDate(expirationDate);
		creditCardBean.setUserId(userId);

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();

		CreditCardInfoEditDao edit = factory.getCreditCardInfoEditDao();
		ConnectionManager.getInstance().beginTransaction();

		if(edit.getSameCreditCardQuantity(cardNo, userId) == 0) { // 同一のクレジットカード既に登録されていたらエラー処理
			if(CreditCardSecurityCodeAuthentication.isCorrectSecurityCode(cardNo, securityCode)) { // セキュリティコード認証
				if(edit.addCreditCardInfo(creditCardBean)) {
					ConnectionManager.getInstance().commit();
					ConnectionManager.getInstance().closeTransaction();
				}else {
					ConnectionManager.getInstance().rollback();
					ConnectionManager.getInstance().closeTransaction();
					//追加エラーメッセージを格納
				}
			}else {
				//認証エラーメッセージを格納
				ConnectionManager.getInstance().rollback();
				ConnectionManager.getInstance().closeTransaction();
			}

		}else {
			// 既に登録されていた時の処理
			ConnectionManager.getInstance().closeTransaction();
		}

		resContext.setTargetCommandPath("creditCardInfoEdit");

		return resContext;
	}

}

