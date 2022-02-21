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

public class GuestUserFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("guestフィルター作動");

		HttpServletRequest httpRequest = (HttpServletRequest)req;
		HttpServletResponse httpResponse = (HttpServletResponse)res;

		UserAndCartBean userAndCartBean = (UserAndCartBean)httpRequest.getSession(true).getAttribute("token");

		if(userAndCartBean == null) { // ログインしてなければゲスト処理へ飛ばす
			System.out.println("ログインしてないのでゲスト処理へ移動");
			filterChain.doFilter(req, res);

		}else {
			req.getRequestDispatcher("/WEB-INF/jsp/error/guestProccessError.jsp").forward(httpRequest,httpResponse);;
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
