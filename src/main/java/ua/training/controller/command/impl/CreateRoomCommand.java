package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.Room;
import ua.training.model.entity.User;
import ua.training.model.service.RoomService;
import ua.training.model.service.RoomTypeService;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class CreateRoomCommand implements Command {
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


        String redirectParams = "reservation_id=" + ((req.getParameter("reservation_id") == null) ? "" : req.getParameter("reservation_id"));


        String roomIdString = req.getParameter("room_id"),
                roomTypeIdString = req.getParameter("room_type_id");
        int roomId, roomTypeId;

        try {
            roomId = Integer.parseInt(roomIdString);
            roomTypeId = Integer.parseInt(roomTypeIdString);
        } catch (NumberFormatException e) {
            return roomTypeIdString.equals("other") ? "redirect:" :  //TODO right redirect to create type
                    "redirect:/app/create_room_page?room_id=" + roomIdString + "&room_type_id=" + roomTypeIdString + "&wrongnumber=error1&" + redirectParams;//TODO right error
        }

        if (!roomService.isExistId(roomId)) {
            if (new RoomTypeService(
                    req.getParameter("language") == null ?
                            req.getSession().getAttribute("language").toString() :
                            req.getParameter("language")
            ).isExistId(roomTypeId)) {
                roomService.createRoom(roomId, roomTypeId);
                return "redirect:/app/room_management_page?"+redirectParams;
            } else {
                return "redirect:/app/create_room_page?room_id=" + roomIdString + "&room_type_id=" + roomTypeIdString + "&notexistroomtype=error2&" + redirectParams;
            }
        } else {
            return "redirect:/app/create_room_page?room_id=" + roomIdString + "&room_type_id=" + roomTypeIdString + "&existroom=error3&" + redirectParams;
        }
    }
}
