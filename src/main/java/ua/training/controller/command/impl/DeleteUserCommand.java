package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.exception.UnsuccessfulSqlOperationException;
import ua.training.model.service.UserService;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();

        HttpSession session = req.getSession();
        String username = req.getParameter("username");

        if (!new UserUtil().validateRole(
                User.Role.ADMIN,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )||session.getAttribute("username").equals(username)) {
            return "redirect:/app/default";
        }
        try {
            new UserService().deleteUser(username);
        } catch (NullPointerException | UnsuccessfulSqlOperationException e){
            return "redirect:/app/view_user_page?username="+username;
        }
        return "redirect:/app/user_management_page";
    }
}
