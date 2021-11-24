package cmd.artist.details;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import context.WebResponseContext;
import dao.AbstractDaoFactory;
import dao.artists.ArtistsDao;

public class ArtistDetailsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {

		RequestContext reqc=getRequestContext();
		resc = new WebResponseContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ArtistsDao dao = factory.getArtistsDao();

		String artistId=reqc.getParameter("artistId")[0];
		resc.setResult(dao.getArtistsDetails(artistId));
		resc.setTargetPath("artistDetails");
		return resc;
    }

}
