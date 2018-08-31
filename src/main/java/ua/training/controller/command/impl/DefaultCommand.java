package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        return manager.getProperty("path.page.index");
    }
}
