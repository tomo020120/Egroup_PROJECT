package cmd.user;

import bean.CreditCardBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.CreditCardInfoEditDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class UpdateCreditCardInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String cardOwnerName = reqContext.getParameter("cardOwnerName")[0];
		String expirationMonth = reqContext.getParameter("month")[0];
		String expirationYear = reqContext.getParameter("year")[0];
		String cardId = reqContext.getParameter("cardId")[0];

		String expirationDate = expirationYear + "/" + expirationMonth;

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		CreditCardInfoEditDao edit = factory.getCreditCardInfoEditDao();

		CreditCardBean creditCardBean = new CreditCardBean();
		creditCardBean.setCardId(cardId);
		creditCardBean.setCardOwnerName(cardOwnerName);
		creditCardBean.setExpirationDate(expirationDate);

		ConnectionManager.getInstance().beginTransaction();

		if(edit.updateCreditCardInfo(creditCardBean)) {
			resContext.setTargetCommandPath("creditCardInfoEdit");

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException("クレジットカード情報更新エラー", null);
		}

		return resContext;
	}

}
