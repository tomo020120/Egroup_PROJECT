package cmd;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import context.RequestContext;

public abstract class CommandFactory{
    public static AbstractCommand getCommand(RequestContext reqContext){
        AbstractCommand command = null;
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream("c:/j2ee4/WEB-INF/classes/property/commands.properties"));
            String name = prop.getProperty(reqContext.getCommandKey());
            Class c = Class.forName(name);
            command = (AbstractCommand)c.newInstance();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return command;
    }
}