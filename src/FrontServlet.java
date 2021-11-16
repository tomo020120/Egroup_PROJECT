import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.ApplicationContext;
import context.ApplicationContextFactory;
import context.RequestContext;
import context.ResponseContext;

public class FrontServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
        doPost(req,res);
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
        req.setCharacterEncoding("utf-8");

        ApplicationContext app = ApplicationContextFactory.getApplicationContext("Web");

        RequestContext reqContext = app.getRequest(req);
        
        String _pictPath=req.getParameter("pictPath");
        System.out.println(_pictPath);
        
        ResponseContext resContext = app.handleRequest(reqContext);

        resContext.setResponse(res);

        app.handleResponse(reqContext,resContext);
    }
}