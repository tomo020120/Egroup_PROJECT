package context;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmd.AbstractCommand;
import cmd.CommandFactory;

public class WebApplicationContext implements ApplicationContext{
    public RequestContext getRequest(Object req){
        RequestContext reqContext = new WebRequestContext();
        reqContext.setRequest(((HttpServletRequest)req));

        return reqContext;
    }

    public ResponseContext handleRequest(RequestContext reqContext){
        AbstractCommand cmd = CommandFactory.getCommand(reqContext);
        cmd.init(reqContext);
        ResponseContext resContext = cmd.execute(new WebResponseContext());

        return resContext;
    }

    public void handleResponse(RequestContext reqContext,ResponseContext resContext){
        HttpServletRequest req = (HttpServletRequest)reqContext.getRequest();
        HttpServletResponse res = (HttpServletResponse)resContext.getResponse();
        
        System.out.println(resContext.getResult());
        req.setAttribute("result",resContext.getResult());
        
        RequestDispatcher dis = req.getRequestDispatcher(resContext.getTargetPath());

        try{
            dis.forward(req,res);
        }
        catch(ServletException e){
        }
        catch(IOException e){
        }
    }
}