package context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WebRequestContext implements RequestContext{
    private HttpServletRequest request;
    private Map<String,String[]> parametersMap;

    public void setRequest(Object request){
        this.request = (HttpServletRequest)request;
        parametersMap = this.request.getParameterMap();
    }

    public Object getRequest(){
        return request;
    }

    public String getCommandKey(){
        String servletPath = request.getServletPath();
        String key = servletPath.substring(1);

        return key;
    }

    public String[] getParameter(String key){
        String[] value = (String[])parametersMap.get(key);

        return value;
    }

    public void setToken(Object token) {
    	request.getSession().setAttribute("token", token);
    }

    public Object getToken() {
		System.out.println("セッション登録値(token) :" + request.getSession().getAttribute("token"));

    	return request.getSession().getAttribute("token");
    }

	public void setSessionAttribute(String attrName, Object value) {
		request.getSession().setAttribute(attrName, value);

	}

	public Object getSessionAttribute(String attrName) {
		System.out.println("セッション登録値 :" + request.getSession().getAttribute(attrName));

		return request.getSession().getAttribute(attrName);
	}

	public void removeSessionAttribute(String attrName) {
		request.getSession().removeAttribute(attrName);
	}

	public void sessionInvalidate() {
		System.out.println("セッションの破棄");
		request.getSession().invalidate();
	}

	public void setPastLocation(String location) {
		request.getSession().setAttribute("pastLocation", location);
	}

	public String getPastLocation() {
		return (String)request.getSession().getAttribute("pastLocation");
	}

	public String getOneBeforeLocation() {
		String referer = request.getHeader("referer");
		String trimLocation = referer.substring(37); // refererからコンテキストパスなどを取り除く

		System.out.println("referer :" + referer);
		System.out.println("referer(トリミング済み) :" + trimLocation);

		if(trimLocation.isEmpty()) {
			trimLocation = "topPage";
		}

		return trimLocation;
	}
}