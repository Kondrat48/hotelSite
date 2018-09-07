package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.RoomService;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteRoomCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if(!new UserUtil().validateRole(
                User.Role.MANAGER,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )){
            return "redirect:/app/default";
        }


        String redirectParams = "reservation_id=" + ((req.getParameter("reservation_id") == null) ? "" : req.getParameter("reservation_id"));


        try{
            int roomId = Integer.valueOf(req.getParameter("room_id"));
            new RoomService().deleteRoomById(roomId);
        }   catch (NumberFormatException e){
            return "redirect:/app/room_management_page?room_id="+req.getParameter("room_id")+"&"+redirectParams;
        }

        return "redirect:/app/edit_user_page?room_id="+req.getParameter("room_id")+"&"+redirectParams;
    }
}
