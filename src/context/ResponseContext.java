package context;

public interface ResponseContext{
	public abstract void setResponse(Object response);
	public abstract Object getResponse();
	public abstract void setResult(Object result);
	public abstract Object getResult();
    public abstract void setResult2(Object result2);
    public abstract Object getResult2();
    public abstract void setTargetPath(String targetPath);
    public abstract String getTargetPath();
}