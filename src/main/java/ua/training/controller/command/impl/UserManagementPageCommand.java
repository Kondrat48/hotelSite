package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.resourceManager.DBColumnManager;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserManagementPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if(!new UserUtil().validateRole(
                User.Role.ADMIN,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )){
            return "redirect:/app/default";
        }

        String current_page = req.getParameter("current_page");
        String records_per_page = req.getParameter("records_per_page");
        int currentPage = current_page==null?1:Integer.parseInt(current_page);
        int recordsPerPage = records_per_page==null?10:Integer.parseInt(records_per_page);
        String sortColumn = req.getParameter("sort_column");

        UserService service = new UserService();
        List<User> users = service
                .getUserList(
                        currentPage,
                        recordsPerPage,
                        sortColumn!=null?sortColumn:new DBColumnManager().getProperty("user.id")
                );
        req.setAttribute("users",users);

        int rows = service.getNumberOfUsers();

        int nOfPages = rows/recordsPerPage;
        nOfPages = nOfPages%recordsPerPage>0?++nOfPages:nOfPages;


        req.setAttribute("sort_column", sortColumn);
        req.setAttribute("no_of_pages", nOfPages);
        req.setAttribute("current_page", currentPage);
        req.setAttribute("records_per_page", recordsPerPage);

        return manager.getProperty("path.page.user_management");
    }
}
