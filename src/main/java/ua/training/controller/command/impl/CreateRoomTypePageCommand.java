package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.RoomService;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class CreateRoomTypePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        RoomService roomService = new RoomService();
        if (!new UserUtil().validateRole(
                User.Role.MANAGER,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )) {
            return "redirect:/app/default";
        }


        return manager.getProperty("path.page.create_room_type");

    }
}
