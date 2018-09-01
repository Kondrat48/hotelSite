package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class RegisterPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if (!req.getSession().getAttribute("role").equals(User.Role.GUEST.name().toLowerCase())) {
            return "redirect:/app/default";
        }
        return manager.getProperty("path.page.register");
    }
}
