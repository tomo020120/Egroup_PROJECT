package event;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartedEvent implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) { // サーバー開始時にメールのヘッダー初期化を行う
		try {
			Class.forName("cmd.mail.SendMail");
			System.out.println("初期化完了");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
