

package cmd;
import context.ResponseContext;
public class login extends AbstractCommand{
    public ResponseContext execute(ResponseContext resContext){
        resContext.setTargetPath("login");
        return resContext;
    }
}