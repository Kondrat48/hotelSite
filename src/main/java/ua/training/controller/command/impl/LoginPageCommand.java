package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class LoginPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if (!req.getSession().getAttribute("role").equals(User.Role.GUEST.name().toLowerCase())) {
            return "redirect:" + manager.getProperty("path.page.index");
        }
        return manager.getProperty("path.page.login");
    }
}
