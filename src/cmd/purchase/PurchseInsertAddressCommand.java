package cmd.purchase;

import java.util.Iterator;
import java.util.List;

import bean.AddressBean;
import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.purchase.PurchaseDao;
import dao.user.DeliveryInfoEditDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class PurchseInsertAddressCommand extends AbstractCommand{

public ResponseContext execute(ResponseContext resContext) {
	RequestContext reqContext = getRequestContext();

	//各配送先情報取得
	String deliveryName = reqContext.getParameter("deliveryName")[0];
	String firstTelephoneNumber = reqContext.getParameter("firstTelephoneNumber")[0];
	String secondTelephoneNumber = reqContext.getParameter("secondTelephoneNumber")[0];
	String thirdTelephoneNumber = reqContext.getParameter("thirdTelephoneNumber")[0];
	String postalCode = reqContext.getParameter("postalCode")[0];
	String address = reqContext.getParameter("address")[0];
	String houseNumber = reqContext.getParameter("houseNumber")[0];
	String buildingName = reqContext.getParameter("buildingName")[0];

	String fullTelephoneNumber = (firstTelephoneNumber + "-" + secondTelephoneNumber + "-" + thirdTelephoneNumber);
	String fullAddress = (address + "/" + houseNumber + "/" + buildingName);

	String userId = ((UserAndCartBean)reqContext.getToken()).getUserId();

	AddressBean addressBean = new AddressBean();


	AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
	DeliveryInfoEditDao edit = factory.getDeliveryInfoEditDao();

	ConnectionManager.getInstance().beginTransaction();

	List<AddressBean> addressList = edit.getDeliveryInfo(userId); // ユーザーが登録してる配送先情報を取得
	Iterator<AddressBean> it = addressList.iterator();

	while(it.hasNext()) { // 登録済みの住所がないかチェック
		addressBean = (AddressBean)it.next();
		System.out.println("ループ住所: " + addressBean.getAddress());
		System.out.println("入力住所: " + fullAddress);
		System.out.println("判定: " + addressBean.getAddress().equals(fullAddress));
		if(addressBean.getAddress().equals(fullAddress)) {
			reqContext.setSessionAttribute("errorFlag", true);
			resContext.setTargetCommandPath("purchase");

			ConnectionManager.getInstance().closeTransaction();

			return resContext;
		}
	}

	addressBean.setDeliveryName(deliveryName);
	addressBean.setTel(fullTelephoneNumber);
	addressBean.setPostalCode(postalCode);
	addressBean.setAddress(fullAddress);
	addressBean.setUserId(userId);

	if(edit.addDeliveryInfo(addressBean)) {

		ConnectionManager.getInstance().beginTransaction();
    	PurchaseDao dao = factory.getPurchaseDao();

    	//UserAndCartBean user=(UserAndCartBean)reqContext.getToken();
    	resContext.setResult(dao.getdeliveryInfoId(userId,fullAddress));

    	Object obj=addressBean.getDeliveryInfoId();
    	System.out.println("obj内容"+obj);
		reqContext.setSessionAttribute("address",obj);

		resContext.setTargetCommandPath("purchase");
		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();
	}else {
		ConnectionManager.getInstance().rollback();
		ConnectionManager.getInstance().closeTransaction();

		throw new IntegrationException("配送先情報新規追加エラー", null);
	}

	return resContext;
}
}


