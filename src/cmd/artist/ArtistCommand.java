package cmd.artist;

import cmd.AbstractCommand;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.artists.ArtistsDao;
import dbManager.ConnectionManager;


public class ArtistCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc){
    	AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
    	ArtistsDao dao = factory.getArtistsDao();

    	ConnectionManager.getInstance().beginTransaction();

    	resc.setResult(dao.getAllArtists());

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

        resc.setTargetPath("artist");

        return resc;

    }
}
