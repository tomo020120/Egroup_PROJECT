package cmd.user;

import bean.CreditCardBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.EditUserInfoDao;

public class AddCreditCardInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		EditUserInfoDao edit = factory.getEditUserInfoDao();//daoを

		String cardOwnerName = reqContext.getParameter("CardOwnerName")[0];
		String cardNo = reqContext.getParameter("CardNo")[0];
		String cardCompany = reqContext.getParameter("CardCompany")[0];
		String expirationDate = reqContext.getParameter("ExpirationDate")[0];
		String securityCode = reqContext.getParameter("SecurityCode")[0];
		String userId = reqContext.getParameter("userId")[0];

		CreditCardBean creditCardBean =new CreditCardBean();

		creditCardBean.setCardOwnerName(cardOwnerName);
		creditCardBean.setCardNo(cardNo);
		creditCardBean.setCardCompany(cardCompany);
		creditCardBean.setExpirationDate(expirationDate);
		creditCardBean.setSecurityCode(securityCode);

		edit.addCreditCard(creditCardBean, userId);


		return resContext;
	}

}
