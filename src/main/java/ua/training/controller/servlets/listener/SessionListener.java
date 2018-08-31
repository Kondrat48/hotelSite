package ua.training.controller.servlets.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

public class SessionListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    public SessionListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("sessions", new HashMap<String, HttpSession>());
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
    public void sessionCreated(HttpSessionEvent se) {
//        se.getSession().setAttribute("language","uk_UA");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        ((Map<String, HttpSession>) se.getSession().getServletContext().getAttribute("sessions")).remove(
                (String) se.getSession().getAttribute("username")
        );
    }

    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {

    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {

    }
}
