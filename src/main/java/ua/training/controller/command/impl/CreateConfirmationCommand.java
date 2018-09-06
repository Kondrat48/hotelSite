package ua.training.controller.command.impl;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class CreateConfirmationCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "redirect:/app/default";
    }
}
