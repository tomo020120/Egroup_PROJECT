package cmd.user;

import java.util.List;

import bean.AddressBean;
import bean.CreditCardBean;
import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.UserInfoEditDao;
import dbManager.ConnectionManager;

public class TransferUserInfoEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {

		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		UserInfoEditDao edit = factory.getEditUserInfoDao();

		UserBean userBean = (UserBean)reqContext.getToken();

		String userId = userBean.getUserId();

		ConnectionManager.getInstance().beginTransaction();

		List<CreditCardBean> cardList = edit.getCreditCardInfo(userId);
		List<AddressBean> addressList = edit.getAddressInfo(userId);

		System.out.println(cardList.size());

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		reqContext.setSessionAttribute("userCardInfo", cardList);
		reqContext.setSessionAttribute("userAddressInfo", addressList);

		resContext.setTargetPath("userInfoEdit");
		return resContext;
	}

}