package ua.training.controller.servlets.servlet;

import ua.training.controller.command.Command;
import ua.training.controller.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.getCommandFromRequest(req);
        String page = command.execute(req);
        if (page.contains("redirect:")) {
            resp.sendRedirect(req.getContextPath() + page.replaceAll(".*redirect:", ""));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
