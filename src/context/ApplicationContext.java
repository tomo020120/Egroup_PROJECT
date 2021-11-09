package context;

public interface ApplicationContext{
    public abstract RequestContext getRequest(Object req);
    public abstract ResponseContext handleRequest(RequestContext reqContext);
    public abstract void handleResponse(RequestContext reqContext,ResponseContext resContext);
}