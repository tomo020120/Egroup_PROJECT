package context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WebRequestContext implements RequestContext{
    private HttpServletRequest request;
    private Map<String,String[]> parametersMap;
    private Object token;

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
    	this.token = token;
    }

    public Object getToken() {
    	return token;
    }
}