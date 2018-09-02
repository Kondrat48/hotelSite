package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.dto.SessionDto;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.resourceManager.DBColumnManager;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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


        String current_page = req.getParameter("current_page");
        String records_per_page = req.getParameter("records_per_page");
        int currentPage = current_page == null ? 1 : Integer.parseInt(current_page);
        int recordsPerPage = records_per_page == null ? 10 : Integer.parseInt(records_per_page);
        String sortColumn = req.getParameter("sort_column");

        List<SessionDto> sessionDtos = new ArrayList<>();
        Map<String, HttpSession> sessionMap = (Map<String, HttpSession>) req.getSession().getServletContext().getAttribute("sessions");
        String usernameToInvalidate = req.getParameter("session_to_remove");
        if (usernameToInvalidate != null) {
            boolean redirect = usernameToInvalidate.equals(req.getSession().getAttribute("username"));
            sessionMap.get(usernameToInvalidate).invalidate();
            if (redirect) {
                return "redirect:/app/default";
            }
        }
        for (HttpSession session : sessionMap.values()) {
            sessionDtos.add(
                    new SessionDto(
                            session,
                            req.getParameter("language") == null?
                                    req.getSession().getAttribute("language").toString() :
                                    req.getParameter("language")
                    )
            );
        }
        sessionDtos.sort((a, b) -> {
            return sortColumn==null||sortColumn.equals("username") ? a.getUsername().compareTo(b.getUsername()) :
                    sortColumn.equals("role") ? a.getRole().compareTo(b.getRole()) :
                            sortColumn.equals("creation") ? a.getCreationTime().compareTo(b.getCreationTime()) :
                                    sortColumn.equals("activity") ? a.getLastActivity().compareTo(b.getLastActivity()) :
                                            sortColumn.equals("language") ? a.getLanguage().compareTo(b.getLanguage()) :
                                                    a.getSessionId().compareTo(b.getSessionId());
        });

        req.setAttribute("sessions", sessionDtos.subList(
                (currentPage-1)*recordsPerPage,
                currentPage*recordsPerPage<=sessionDtos.size()?
                        currentPage*recordsPerPage:
                        currentPage*recordsPerPage-(recordsPerPage-sessionDtos.size())
        ));

        int rows = sessionDtos.size();

        int nOfPages = rows / recordsPerPage;
        nOfPages = nOfPages % recordsPerPage > 0 ? ++nOfPages : nOfPages;


        req.setAttribute("sort_column", sortColumn);
        req.setAttribute("no_of_pages", nOfPages);
        req.setAttribute("current_page", currentPage);
        req.setAttribute("records_per_page", recordsPerPage);


        return manager.getProperty("path.page.session_management");
    }
}
