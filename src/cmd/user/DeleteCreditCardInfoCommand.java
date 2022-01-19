package cmd.user;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.CreditCardInfoEditDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class DeleteCreditCardInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String cardId = reqContext.getParameter("cardId")[0];

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		CreditCardInfoEditDao edit = factory.getCreditCardInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		if(edit.deleteCreditCardInfo(cardId)) {
			resContext.setTargetCommandPath("creditCardInfoEdit");
			//消去完了メッセージ

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException("クレジットカード情報消去エラー", null);
		}

		return resContext;
	}

}
