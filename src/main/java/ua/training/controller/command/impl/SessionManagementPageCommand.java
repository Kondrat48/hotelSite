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
            return "redirect:/app/default";
        }


        String current_page = req.getParameter("current_page");
        String records_per_page = req.getParameter("records_per_page");
        int currentPage = current_page == null ? 1 : Integer.parseInt(current_page);
        int recordsPerPage = records_per_page == null ? 10 : Integer.parseInt(records_per_page);
        String sort_column = req.getParameter("sort_column");
        String search_field = req.getParameter("search_field");
        String search_param = req.getParameter("search_param");

        search_field = search_field == null || search_field.equals("") ? "username" : search_field;
        search_param = search_param == null ? "" : search_param;

        List<SessionDto> sessionDtos = new ArrayList<>();
        Map<String, HttpSession> sessionMap = (Map<String, HttpSession>) req.getSession().getServletContext().getAttribute("sessions");
        String usernameToInvalidate = req.getParameter("session_to_remove");
        if (usernameToInvalidate != null && sessionMap.get(usernameToInvalidate) != null) {
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
                            req.getParameter("language") == null ?
                                    req.getSession().getAttribute("language").toString() :
                                    req.getParameter("language")
                    )
            );
        }
        sessionDtos.sort((a, b) -> {
            return sort_column == null || sort_column.equals("username") ? a.getUsername().compareTo(b.getUsername()) :
                    sort_column.equals("role") ? a.getRole().compareTo(b.getRole()) :
                            sort_column.equals("creation") ? a.getCreationTime().compareTo(b.getCreationTime()) :
                                    sort_column.equals("activity") ? a.getLastActivity().compareTo(b.getLastActivity()) :
                                            sort_column.equals("language") ? a.getLanguage().compareTo(b.getLanguage()) :
                                                    a.getSessionId().compareTo(b.getSessionId());
        });

        Iterator<SessionDto> iterator = sessionDtos.iterator();
        while (iterator.hasNext()) {
            SessionDto sessionDto = iterator.next();
            switch (search_field) {
                case "id":
                    if (!sessionDto.getSessionId().contains(search_param)) {
                        iterator.remove();
                    }
                    break;
                case "role":
                    if (!sessionDto.getRole().contains(search_param)) {
                        iterator.remove();
                    }
                    break;
                default:
                    if (!sessionDto.getUsername().contains(search_param)) {
                        iterator.remove();
                    }
                    break;
            }
        }
        req.setAttribute("sessions", sessionDtos.subList(
                (currentPage - 1) * recordsPerPage,
                currentPage * recordsPerPage <= sessionDtos.size() ?
                        currentPage * recordsPerPage :
                        sessionDtos.size()
        ));


        int rows = sessionDtos.size();

        int nOfPages = rows / recordsPerPage;
        nOfPages = rows % recordsPerPage > 0 ? ++nOfPages : nOfPages;

        currentPage = rows < currentPage * recordsPerPage && currentPage != 1 ? --currentPage : currentPage;

        req.setAttribute("sort_column", sort_column);
        req.setAttribute("no_of_pages", nOfPages);
        req.setAttribute("current_page", currentPage);
        req.setAttribute("records_per_page", recordsPerPage);
        req.setAttribute("search_param", search_param);
        req.setAttribute("search_field", search_field);
        req.setAttribute("search_fields", new ArrayList<>(Arrays.asList("username", "role", "id")));


        return manager.getProperty("path.page.session_management");
    }
}
