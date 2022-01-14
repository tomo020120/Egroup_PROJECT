package cmd.artist.details;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.artists.ArtistsDao;
import dbManager.ConnectionManager;

public class ArtistDetailsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {

		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ArtistsDao dao = factory.getArtistsDao();

		String artistId=reqc.getParameter("artistId")[0];

		ConnectionManager.getInstance().beginTransaction();

		resc.setResult(dao.getArtistsDetails(artistId));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("artistDetails");
		return resc;
    }

}
