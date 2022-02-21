package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.joinBean.UserAndCartBean;

public class AdminCheckFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("adminフィルター作動");

		HttpServletRequest httpRequest = (HttpServletRequest)req;
		HttpServletResponse httpResponse = (HttpServletResponse)res;

		UserAndCartBean userAndCartBean = (UserAndCartBean)httpRequest.getSession(true).getAttribute("token");

		if(userAndCartBean == null) { // ログインしてなければログイン画面へ飛ばす
			System.out.println("ログインしてないのでログイン画面へ移動");

			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(httpRequest,httpResponse);
		}else if(userAndCartBean.getUserName() == "管理者") { // 管理者の場合はページに飛ばす。
			filterChain.doFilter(req, res);
		}else {
			req.getRequestDispatcher("/WEB-INF/jsp/switchLoginUser.jsp").forward(httpRequest,httpResponse);;
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
