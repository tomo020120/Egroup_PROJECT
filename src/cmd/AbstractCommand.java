package cmd;

import context.RequestContext;
import context.ResponseContext;

public abstract class AbstractCommand {
	private RequestContext reqContext;

	public void init(RequestContext reqContext) {
		this.reqContext =reqContext;
	}

	protected RequestContext getRequestContext() {
		return reqContext;
	}

	public abstract ResponseContext execute(ResponseContext resContext);

}
