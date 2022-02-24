package context;

public interface RequestContext{
    public abstract void setRequest(Object request);
    public abstract Object getRequest();
    public abstract String getRequestPath();
    public abstract String getCommandKey();
    public abstract String[] getParameter(String key);
    public abstract boolean uploadFile();
    public abstract boolean isNoFile();
    public abstract boolean deletePictFile(String pictPath);
    public abstract String getAbsoluteFilePath();
    public abstract String getPictFileName();
    public abstract void setToken(Object token);
    public abstract Object getToken();
    public abstract void setSessionAttribute(String attrName,Object value);
    public abstract Object getSessionAttribute(String attrName);
    public abstract void removeSessionAttribute(String attrName);
    public abstract void sessionInvalidate();
    public abstract void setPastLocation(String location);
    public abstract String getPastLocation();
    public abstract String getOneBeforeLocation();
}