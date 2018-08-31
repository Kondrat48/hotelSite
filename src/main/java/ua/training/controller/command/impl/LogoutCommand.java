package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:"+new PagePathManager().getProperty("path.page.index");
    }
}