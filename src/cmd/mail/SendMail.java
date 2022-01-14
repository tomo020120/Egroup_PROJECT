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
	private static final String ORNER_MAILADDRESS_PASS = "ibanez0120";
	private static Session session = null;
	private static MimeMessage mimeMessage = null;

	static { // 一度だけ初期化
		property = new Properties();
		property.put("mail.smtp.host", "smtp.gmail.com");
		property.put("mail.smtp.auth", "true");
		property.put("mail.smtp.starttls.enable", "true");
		property.put("mail.smtp.host", "smtp.gmail.com");
		property.put("mail.smtp.port", "587");
		property.put("mail.smtp.debug", "true");
		session = Session.getDefaultInstance(property, new javax.mail.Authenticator() { // sessionに設定情報とSMTP認証を行う
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
			mimeMessage.setText(messageBody, "ISO-2022-JP");
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
			mimeMessage.setText(messageBody, "ISO-2022-JP");
			Transport.send(mimeMessage);
			System.out.println("メール送信が完了しました。");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MailSendException(e.getMessage(),e);
		}
	}
}