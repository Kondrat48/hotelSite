package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.dto.SessionDto;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SessionManagementPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if (!new UserUtil().validateRole(
                User.Role.ADMIN,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )) {
            return "redirect:" + manager.getProperty("path.page.index");
        }
        List<SessionDto> sessionDtos = new ArrayList<>();
        Map<String, HttpSession> sessionMap = (Map<String, HttpSession>) req.getSession().getServletContext().getAttribute("sessions");
        String usernameToInvalidate = req.getParameter("session_to_remove");
        if (usernameToInvalidate != null) {
            boolean redirect = usernameToInvalidate.equals(req.getSession().getAttribute("username"));
            sessionMap.get(usernameToInvalidate).invalidate();
            if(redirect){
                return "redirect:/app/default";
            }
        }
        for (HttpSession session : sessionMap.values()) {
            sessionDtos.add(new SessionDto(session, req.getSession().getAttribute("language").toString()));
        }
        req.setAttribute("sessions", sessionDtos);
        return manager.getProperty("path.page.session_management");
    }
}
