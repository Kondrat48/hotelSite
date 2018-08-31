package ua.training.controller.servlets.filter;

import ua.training.controller.utils.UserUtil;
import ua.training.controller.exception.AccessDeniedException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        UserUtil util = new UserUtil();
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        String uri = req.getRequestURI();
        if(util.validateUri(role, uri)){
            chain.doFilter(req,resp);
            return;
        }
        throw new AccessDeniedException();


    }




    @Override
    public void destroy() {

    }
}
