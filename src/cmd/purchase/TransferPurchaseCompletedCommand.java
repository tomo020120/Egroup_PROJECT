package cmd.purchase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import bean.AddressBean;
import bean.CreditCardBean;
import bean.joinBean.AllCartBean;
import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import cmd.mail.SendMail;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.purchase.PurchaseDao;
import dbManager.ConnectionManager;

public class TransferPurchaseCompletedCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqContext = getRequestContext();

		UserAndCartBean userAndCartBean = (UserAndCartBean)reqContext.getToken();

		String userId = userAndCartBean.getUserId();
		String cartId = userAndCartBean.getCartId();

		int totalAmount = (userAndCartBean.getTotal() + 500);

		String deliveryInfoId = reqContext.getSessionAttribute("address").toString();
		String cardId = reqContext.getSessionAttribute("creditCard").toString();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		PurchaseDao dao = factory.getPurchaseDao();
    	ConnectionManager.getInstance().beginTransaction();

    	AllCartBean all=dao.getInsideCart(cartId); // カートの中身取得

    	String ItemId=all.getItemId();
    	String OrderCount=all.getOrderCount();
    	String subTotal=all.getSubTotal();

    	if(dao.PurchaseCompleted(ItemId,OrderCount,subTotal,cartId,totalAmount,userId)) {
    		AddressBean addressBean = dao.getTargetAddressInfo(deliveryInfoId);
    		CreditCardBean creditCardBean = dao.getTargetCreditCardInfo(cardId);
    		String url = reqContext.getRequestPath();

			String htmlText = creataPurchaseCompleteMessageBody(userAndCartBean.getUserName(), totalAmount, addressBean, creditCardBean,url);

			SendMail.send(userAndCartBean.getMailAddress(),htmlText);

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();
		}
    	resc.setTargetPath("completed");

		return resc;
	}

	private String creataPurchaseCompleteMessageBody(String userName,int totalAmount,AddressBean addressBean,CreditCardBean creditCardBean,String url) { // 購入完了メール文の生成
		StringBuilder content = new StringBuilder();

		content.append("<h1 style=\"font-size:25px;\">ご注文の確認</h1>");
		content.append("<h2 style=\"font-size:18px;\">" + userName + "様</h2>");

		String topUrl = (url + "Egroup_PROJECT/");
		String purchaseHistoryUrl = (url + "Egroup_PROJECT/purchaseHistory");

		String sentence1 = ("<a href=" + topUrl + ">Ibanez</a>をご利用いただき、ありがとうございます。<a href=\"http://localhost:8080/Egroup_PROJECT/\">Ibanez</a>"
				+ "がお客様のご注文を承ったことをお知らせいたします。<br>"
				+ "詳細は<a href=" + purchaseHistoryUrl + ">注文履歴</a>からご確認ください。<br>"
				+ "商品が発送されましたら、Eメールにてお知らせいたします。");

		content.append("<p>" + sentence1 + "</p>");

		String fastestDay = getDeliveryFastestDay();
		String latestDay = getDeliveryLatestDay();

		String deliveryScheduled = (fastestDay + " - " + latestDay);

		// 各お届け先情報取得
		String deliveryName = addressBean.getDeliveryName();
		String postalCode = addressBean.getPostalCode();
		String address = addressBean.getAddress();

		String cardNo = creditCardBean.getCardNo();
		String cardCompany = creditCardBean.getCardCompany();

		content.append("<div style=\"display:flex;width:700px;border-width:10px;border-style:double;border-color:#333333;padding-top:5px;padding-bottom:5px;padding-right:5px;padding-left:5px;margin-top:0px;margin-bottom:10px;margin-right:0px;margin-left:0px;\">");

		content.append("<div><p>お届け予定日:</p><p>" + deliveryScheduled + "</p></div>"); // お届け予定文

		content.append("<div><p style=\"margin-left:20px;\">お届け先:<p><ul style=\"list-style-type:none;padding-left:20px;\">");
		content.append("<li>" + deliveryName + "様</li>");
		content.append("<li>" + postalCode + "</li>");
		content.append("<li>" + address + "</li>");

		content.append("<li>注文合計:" + totalAmount + "円</li>");

		content.append("<li>支払い方法:クレジットカード</li>");
		content.append("<li>末尾" + cardNo + "(" + cardCompany + ")</li>");

		content.append("</ul></div>");

		content.append("</div>");

		return content.toString();
	}

	private String getDeliveryFastestDay() { // 配達の最速の日時を返す(現在日時の2日後)
		String strCurrentDaySentense = "";

		Date date = new Date();

		Calendar calender = Calendar.getInstance();
		calender.setTime(date);

		calender.add(Calendar.DAY_OF_MONTH, 2); // 現在日時の2日後のデータ取得

		date = calender.getTime();

		TimeZone timezone = TimeZone.getTimeZone("Asia/Tokyo");
		DateFormat requiredFormat = new SimpleDateFormat("M/d");
		requiredFormat.setTimeZone(timezone);
		String currentDate = requiredFormat.format(date).toUpperCase();

		requiredFormat = new SimpleDateFormat("EEEEEE");
		requiredFormat.setTimeZone(timezone);
		String currentDayOfWeek = requiredFormat.format(date).toUpperCase();

		strCurrentDaySentense = currentDayOfWeek + "," + currentDate;

		return  strCurrentDaySentense;
	}

	private String getDeliveryLatestDay() { // 配達の最遅の日時を返す(現在日時の4日後)
		String strFourDaysLaterSentense = "";

		Date date = new Date();

		Calendar calender = Calendar.getInstance();
		calender.setTime(date);

		calender.add(Calendar.DAY_OF_MONTH, 4); // 現在日時の4日後のデータ取得

		date = calender.getTime();

		TimeZone timezone = TimeZone.getTimeZone("Asia/Tokyo");
		DateFormat requiredFormat = new SimpleDateFormat("M/d");
		requiredFormat.setTimeZone(timezone);
		String fourDaysLaterDate = requiredFormat.format(date).toUpperCase();

		requiredFormat = new SimpleDateFormat("EEEEEE");
		requiredFormat.setTimeZone(timezone);
		String fourDaysLaterOfWeek = requiredFormat.format(date).toUpperCase();

		strFourDaysLaterSentense = fourDaysLaterOfWeek + "," + fourDaysLaterDate;

		return  strFourDaysLaterSentense;
	}
}