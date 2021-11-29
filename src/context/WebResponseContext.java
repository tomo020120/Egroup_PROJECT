package context;

import javax.servlet.http.HttpServletResponse;

public class WebResponseContext implements ResponseContext{
    private Object result;
    private Object message;
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

    public void setMessage(Object message) {
    	this.message = message;
    }

    public Object getMessage() {
    	return message;
    }

    public void setTargetPath(String targetPath){
    	this.targetPath = "/WEB-INF/jsp/" + targetPath + ".jsp";
    }

    public String getTargetPath(){
        return targetPath;
    }

	public String setTargetCommandPath(String commandPath) {
		this.targetPath = "/" + commandPath;

		return targetPath;
	}
}