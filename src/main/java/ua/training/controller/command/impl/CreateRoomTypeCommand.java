package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.RoomService;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

public class CreateRoomTypeCommand implements Command {
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

        String nameUa = req.getParameter("name_ua"),
                nameEn = req.getParameter("name_en"),
                descriptionUa = req.getParameter("description_ua"),
                descriptionEn = req.getParameter("description_en"),
                imagePath = req.getParameter("image_path"),
                placesString = req.getParameter("places"),
                pricePerNightString = req.getParameter("price_per_night");
        int places;
        long pricePerNight;
        try {
            places = Integer.valueOf(placesString);
            pricePerNight = Long.valueOf(pricePerNightString);

        }catch (NumberFormatException e) {
            return "/app/crate_room_type_page";
        }


        return null;
    }


}
