package cmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import context.RequestContext;

public abstract class CommandFactory{
	private static final String COMMAND_PROPERTY_PATH = new File("src\\property\\commands.properties").getAbsolutePath();

    public static AbstractCommand getCommand(RequestContext reqContext){
        AbstractCommand command = null;
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream(COMMAND_PROPERTY_PATH));
            String name = prop.getProperty(reqContext.getCommandKey());
            Class<?> c = Class.forName(name);
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