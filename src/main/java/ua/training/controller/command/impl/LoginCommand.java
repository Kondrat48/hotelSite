package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.RegexpUtil;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.resourceManager.MessageManager;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();

        if (!req.getSession().getAttribute("role").equals(User.Role.GUEST.name().toLowerCase())) {
            return "redirect:/app/default";
        }

        Locale locale = new Locale((String) req.getSession().getAttribute("language"));
        RegexpUtil regexUtil = new RegexpUtil(locale);
        UserService userService = new UserService();


        Map<String, String> errorMap = new HashMap<>();

        String username = "username",
                password = "password";


        regexUtil.validate(errorMap, req.getParameter(username), "regexp.username",username);
        regexUtil.validate(errorMap, req.getParameter(password), "regexp.password",password);

        if (errorMap.size() > 0) {
            for (String error : errorMap.keySet()) {
                req.setAttribute(error, errorMap.get(error));
            }
            return manager.getProperty("path.page.login");
        }


        if (userService.checkLoginPassword(req.getParameter(username), req.getParameter(password))) {
            Map<String, HttpSession> sessionMap = (Map<String, HttpSession>)req.getSession().getServletContext().getAttribute("sessions");
            if (req.getSession().getAttribute(username) != null && req.getSession().getAttribute(username).equals(req.getParameter(username))) {
                ((HttpSession) req.getSession().getServletContext().getAttribute(req.getParameter(username))).invalidate();
            }
            sessionMap.get(req.getSession().getAttribute(username)).invalidate();
            req.getSession().setAttribute(username, req.getParameter(username));
            req.getSession().setAttribute("role", userService.getUserRole(req.getParameter(username)).name().toLowerCase());
            if(sessionMap.keySet().contains(req.getSession().getAttribute(username))){
                sessionMap.get(req.getSession().getAttribute(username)).invalidate();
            }
            sessionMap.put(req.getParameter(username), req.getSession());
            return "redirect:/app/default";
        }

        req.setAttribute("errorLogin", new MessageManager(locale).getProperty("error.login"));
        req.setAttribute("username",username);
        return manager.getProperty("path.page.login");
    }
}