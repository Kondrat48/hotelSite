package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.RoomTypeService;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class CreateRoomPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if (!new UserUtil().validateRole(
                User.Role.MANAGER,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )) {
            return "redirect:/app/default";
        }


        String redirectParams = "reservation_id=" + ((req.getParameter("reservation_id") == null) ? "" : req.getParameter("reservation_id"));


        req.setAttribute("roomTypes", new RoomTypeService(
                req.getParameter("language") == null ?
                        req.getSession().getAttribute("language").toString() :
                        req.getParameter("language")
        ).getAllTypes());

        return manager.getProperty("path.page.create_room") + "?" + redirectParams;
    }
}
