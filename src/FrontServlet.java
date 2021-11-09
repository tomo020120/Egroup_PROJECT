import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmd.*;
import context.*;

public class FrontServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
        doPost(req,res);
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
        req.setCharacterEncoding("utf-8");

        ApplicationContext app = new WebApplicationContext();

        RequestContext reqContext = app.getRequest(req);

        ResponseContext resContext = app.handleRequest(reqContext);

        resContext.setResponse(res);

        app.handleResponse(reqContext,resContext);
    }
}