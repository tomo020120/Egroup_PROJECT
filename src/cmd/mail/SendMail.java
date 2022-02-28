package cmd.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ex.MailSendException;

public abstract class SendMail{
	private static Properties property = null;
	private static final String ORNER_MAILADDRESS = "ibanezttc@gmail.com"; // 運営メールアドレス
	private static final String ORNER_MAILADDRESS_PASS = "jtendrxjqcxenjgt"; // AWSバージョン
	//private static final String ORNER_MAILADDRESS_PASS = "ibanez0120"; // ローカルバージョン
	private static Session session = null;
	private static MimeMessage mimeMessage = null;

	static { // 一度だけ初期化
		System.out.println("メールプロパティ初期化開始");
		property = new Properties();
		property.put("mail.smtp.host", "smtp.gmail.com");
		property.put("mail.smtp.auth", "true");
		property.put("mail.smtp.starttls.enable", "true");
		property.put("mail.smtp.port", "587");
		property.put("mail.smtp.debug", "true");
		session = Session.getInstance(property, new javax.mail.Authenticator() { // sessionに設定情報とSMTP認証を行う
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ORNER_MAILADDRESS, ORNER_MAILADDRESS_PASS);
			}
		});
		mimeMessage = new MimeMessage(session);
	}

	public static void send(String userMailAddress,String messageBody) {
		try {
			InternetAddress toAddress = new InternetAddress(userMailAddress, "お客様");
			mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
			InternetAddress fromAddress = new InternetAddress("ibanezttc@gmail.com", "Ibanez");
			mimeMessage.setFrom(fromAddress);
			mimeMessage.setSubject("【Ibanez】お買い上げ頂きありがとうございます。", "ISO-2022-JP");
			mimeMessage.setContent(createHTML(messageBody),"text/html; charset=ISO-2022-JP");
			Transport.send(mimeMessage);
			System.out.println("メール送信が完了しました。");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MailSendException(e.getMessage(),e);
		}
	}

	public static void send(String userMailAddress,String messageBody,String title) { // タイトルを変更したいとき用のオーバーロード
		try {
			InternetAddress toAddress = new InternetAddress(userMailAddress, "お客様");
			mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
			InternetAddress fromAddress = new InternetAddress("ibanezttc@gmail.com", "Ibanez");
			mimeMessage.setFrom(fromAddress);
			mimeMessage.setSubject(title, "ISO-2022-JP");
			mimeMessage.setContent(createHTML(messageBody),"text/html; charset=ISO-2022-JP");
			Transport.send(mimeMessage);
			System.out.println("メール送信が完了しました。");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MailSendException(e.getMessage(),e);
		}
	}

	private static String createHTML(String messageBody) { // HTMLメール基本フォーマット作成メソッド
		StringBuilder b = new StringBuilder();
		b.append("<html><head>");
		b.append("<style type=\"text/css\">");
		b.append("ul {background-color: #fff; width:100%;}");
		b.append("li {font-size: 1em; padding: 0; margin: 0; background-color: #fff;}");
		b.append("</style>");
		b.append("</head>");
		b.append("<body>");
		b.append(messageBody);
		b.append("</body></html>");

		return b.toString();
	}
}