package cmd.favorite;

import bean.FavoriteBean;
import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.favorite.FavoriteDao;
import dbManager.ConnectionManager;

public class AddFavoriteCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		FavoriteDao favorite = factory.getFavoriteDao();

		String userId = ((UserAndCartBean)reqContext.getToken()).getUserId();
		String itemId = reqContext.getParameter("itemId")[0];

		FavoriteBean favoriteBean = new FavoriteBean();

		favoriteBean.setItemId(itemId);
		favoriteBean.setUserId(userId);

		ConnectionManager.getInstance().beginTransaction();

		if(favorite.addFavorite(favoriteBean)) {
			resContext.setTargetCommandPath("productsDetails?itemId=" + itemId);
			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();
		}

		return resContext;
	}

}
