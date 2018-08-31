package ua.training.controller.servlets.filter;

import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class GuestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        String role = (String) req.getSession().getAttribute("role");

        if (role == null) {
            String username = new UserUtil().getRandomUniqueUsername(req.getSession());
            req.getSession().setAttribute("role", User.Role.GUEST.name().toLowerCase());
            req.getSession().setAttribute("username", username);
            Map<String, HttpSession> sessionMap = (Map<String, HttpSession>)req.getSession().getServletContext().getAttribute("sessions");
            req.getSession().setMaxInactiveInterval(60);
            sessionMap.put(username, req.getSession());
            RequestDispatcher dispatcher = req.getRequestDispatcher(new PagePathManager().getProperty("path.page.index"));
            dispatcher.forward(req,resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
