package context;

public interface RequestContext{
    public abstract void setRequest(Object request);
    public abstract Object getRequest();
    public abstract String getCommandKey();
    public abstract String[] getParameter(String key);
    public abstract void setToken(Object token);
    public abstract Object getToken();
}