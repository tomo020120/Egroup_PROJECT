package cmd.user;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;

public class TransferDeleteDeliveryInfoComfirmCommand extends AbstractCommand {
	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		String deliveryInfoId = reqContext.getParameter("deliveryInfoId")[0];

		resContext.setResult(deliveryInfoId);

		resContext.setTargetPath("deleteDeliveryInfoComfirm");
		return resContext;
	}
}
