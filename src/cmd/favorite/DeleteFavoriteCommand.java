package cmd.favorite;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.favorite.FavoriteDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class DeleteFavoriteCommand extends AbstractCommand {
	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		FavoriteDao favorite = factory.getFavoriteDao();

		String itemId = reqContext.getParameter("itemId")[0];

		String userId = ((UserAndCartBean)reqContext.getToken()).getUserId();

		if(favorite.deleteFavorite(itemId,userId)) {
			System.out.println("cartinside削除");

			resContext.setTargetCommandPath("products");

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();

		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException(null, null);

		}
				return resContext;

	}

}
