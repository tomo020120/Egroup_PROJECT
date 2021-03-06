package cmd.user.guest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import cmd.AbstractCommand;
import cmd.mail.SendMail;
import context.RequestContext;
import context.ResponseContext;

public class GuestPurchaseDetermineCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String sendMailDescription = reqContext.getParameter("sendMailDescription")[0];
		String[] sendMailDescriptionArray = sendMailDescription.split(",");

		String userName = sendMailDescriptionArray[4];
		String mailAddress = sendMailDescriptionArray[5];

		String url = reqContext.getRequestPath();

		String htmlText = creataPurchaseCompleteMessageBody(userName, sendMailDescriptionArray,url);

		SendMail.send(mailAddress,htmlText);

		resContext.setTargetPath("completed");

		return resContext;
	}

	private String creataPurchaseCompleteMessageBody(String userName,String[] descriptionArray,String url) { // 購入完了メール文の生成
		StringBuilder content = new StringBuilder();

		content.append("<h1 style=\"font-size:25px;\">ご注文の確認</h1>");
		content.append("<h2 style=\"font-size:18px;\">" + userName + "様</h2>");

		String topUrl = (url + "Egroup_PROJECT/");
		String purchaseHistoryUrl = (url + "Egroup_PROJECT/purchaseHistory");

		String sentence1 = ("<a href=" + topUrl + ">Ibanez</a>をご利用いただき、ありがとうございます。<a href=\"http://localhost:8080/Egroup_PROJECT/\">Ibanez</a>"
				+ "がお客様のご注文を承ったことをお知らせいたします。<br>"
				+ "詳細は<a href=" + purchaseHistoryUrl +">注文履歴</a>からご確認ください。<br>"
				+ "商品が発送されましたら、Eメールにてお知らせいたします。");

		content.append("<p>" + sentence1 + "</p>");

		String fastestDay = getDeliveryFastestDay();
		String latestDay = getDeliveryLatestDay();

		String deliveryScheduled = (fastestDay + " - " + latestDay);

		// 各お届け先情報取得
		String deliveryName = descriptionArray[6];
		String postalCode = descriptionArray[8];
		String address = descriptionArray[9];

		String cardNo = descriptionArray[10];
		String cardCompany = descriptionArray[11];

		String totalAmount = descriptionArray[0];

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
