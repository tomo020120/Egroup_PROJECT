package cmd.artist;

import cmd.AbstractCommand;
import context.ResponseContext;


public class ArtistCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resContext){
        resContext.setTargetPath("artist");

        return resContext;

    }
}