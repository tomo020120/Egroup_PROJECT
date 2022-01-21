package cmd.favorite;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.favorite.FavoriteDao;
import dbManager.ConnectionManager;

public class TransferFavoriteListPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		AbstractDaoFactory factory=AbstractDaoFactory.getDaoFactory();
		FavoriteDao dao= factory.getFavoriteDao();

		String userId = ((UserAndCartBean)reqContext.getToken()).getUserId();

		ConnectionManager.getInstance().beginTransaction();

		resContext.setResult(dao.getFavoriteList(userId));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();


		resContext.setTargetPath("favoriteListPage");
		return resContext;
	}
}
