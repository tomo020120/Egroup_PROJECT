package cmd;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import context.RequestContext;

public abstract class CommandFactory{
	private static final String COMMAND_PROPERTY_PATH = "c:\\property\\commands.properties";
	//private static final String COMMAND_PROPERTY_PATH = new File("..\\workspace\\Egroup_PROJECT\\src\\property\\commands.properties").getAbsolutePath();

    public static AbstractCommand getCommand(RequestContext reqContext){
        AbstractCommand command = null;
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream(COMMAND_PROPERTY_PATH));

            System.out.println("キー値：" + reqContext.getCommandKey());

            String name = prop.getProperty(reqContext.getCommandKey());

            System.out.println("取得プロパティ：" + name);
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