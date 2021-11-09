package context;

public interface RequestContext{
    public abstract void setRequest(Object request);
    public abstract Object getRequest();
    public abstract String getCommandKey();
    public abstract String[] getParameter(String key);
}