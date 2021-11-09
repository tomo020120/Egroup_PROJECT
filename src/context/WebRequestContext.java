package context;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebRequestContext implements RequestContext{
    private HttpServletRequest request;
    private Map parametersMap;

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
}