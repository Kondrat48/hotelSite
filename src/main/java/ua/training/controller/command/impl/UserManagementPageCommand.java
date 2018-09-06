package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.resourceManager.DBColumnManager;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserManagementPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if (!new UserUtil().validateRole(
                User.Role.MANAGER,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )) {
            return "redirect:/app/default";
        }

        String currentPageString = req.getParameter("current_page");
        String recordsPerPageString = req.getParameter("records_per_page");
        int currentPage = currentPageString == null ? 1 : Integer.parseInt(currentPageString);
        int recordsPerPage = recordsPerPageString == null ? 10 : Integer.parseInt(recordsPerPageString);
        String sortColumn = req.getParameter("sort_column");
        String searchField = req.getParameter("search_field");
        String searchParam = req.getParameter("search_param");

        UserService service = new UserService();
        List<User> users = service
                .getUserList(
                        currentPage,
                        recordsPerPage,
                        sortColumn != null ? sortColumn : new DBColumnManager().getProperty("user.id"),
                        searchParam,
                        searchField
                );
        req.setAttribute("users", users);

        int rows = service.getNumberOfUsers(searchParam, searchField);

        int nOfPages = rows / recordsPerPage;
        nOfPages = rows % recordsPerPage > 0 ? ++nOfPages : nOfPages;

        currentPage = rows < currentPage * recordsPerPage && currentPage != 1 ? --currentPage : currentPage;

        req.setAttribute("sort_column", sortColumn);
        req.setAttribute("no_of_pages", nOfPages);
        req.setAttribute("current_page", currentPage);
        req.setAttribute("records_per_page", recordsPerPage);
        req.setAttribute("search_param", searchParam);
        req.setAttribute("search_field", searchField);
        req.setAttribute("search_fields", new ArrayList<>(Arrays.asList("id", "username", "name", "surname", "email")));

        return manager.getProperty("path.page.user_management");
    }
}
