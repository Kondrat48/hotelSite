package ua.training.controller.command;

import ua.training.controller.command.impl.DefaultCommand;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    public Command getCommandFromRequest(HttpServletRequest req) {
        String commandName = req.getRequestURI();
        commandName = commandName.replaceAll(".*/app/", "");

        Command command = new DefaultCommand();

        if (commandName.isEmpty()) {
            return command;
        }
        try {
            CommandEnum commandEnum = CommandEnum.valueOf(commandName.toUpperCase());
            command = commandEnum.getCommand();
        }catch (IllegalArgumentException e){
            req.setAttribute("wrongAction", commandName);
        }
        return command;
    }
}
