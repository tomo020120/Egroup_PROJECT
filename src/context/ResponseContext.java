package context;

public interface ResponseContext{
	public abstract void setResponse(Object response);
	public abstract Object getResponse();
	public abstract void setResult(Object result);
	public abstract Object getResult();
    public abstract void setMessage(Object message);
    public abstract Object getMessage();
    public abstract void setTargetPath(String targetPath);
    public abstract String setTargetCommandPath(String commandPath);
    public abstract String getTargetPath();
}