package cmd.artist;

import cmd.AbstractCommand;
import context.ResponseContext;
import context.WebResponseContext;
import dao.AbstractDaoFactory;
import dao.artists.ArtistsDao;


public class ArtistCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc){
    	resc = new WebResponseContext();
    	AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
    	ArtistsDao dao = factory.getArtistsDao();
    	resc.setResult(dao.getAllArtists());
        resc.setTargetPath("artist");

        return resc;

    }
}
