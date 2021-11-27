package context;

import javax.servlet.http.HttpServletResponse;

public class WebResponseContext implements ResponseContext{
    private Object result;
    private Object result2;
    private Object token;
    private String targetPath;
    private HttpServletResponse response;

    public void setResponse(Object response){
        this.response = (HttpServletResponse)response;
    }

    public Object getResponse(){
        return response;
    }

    public void setResult(Object result){
        this.result = result;
    }

    public Object getResult(){
        return result;
    }

    public void setResult2(Object result2) {
    	this.result2 = result2;
    }

    public Object getResult2() {
    	return result2;
    }

    public void setTargetPath(String targetPath){
        this.targetPath = "/WEB-INF/jsp/" + targetPath + ".jsp";
    }

    public String getTargetPath(){
        return targetPath;
    }

	public void setSessionToken(Object token) {
		this.token = token;
	}

	public Object getSessionToken() {
		return token;
	}
}