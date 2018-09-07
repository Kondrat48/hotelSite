package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.dto.RoomDto;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.Room;
import ua.training.model.entity.User;
import ua.training.model.service.RoomService;
import ua.training.model.service.resourceManager.DBColumnManager;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomManagementPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if (!new UserUtil().validateRole(
                User.Role.MANAGER,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )) {
            return "redirect:/app/default";
        }

        String currentPageString = req.getParameter("current_page");
        String recordsPerPageString = req.getParameter("records_per_page");
        int currentPage = currentPageString == null ? 1 : Integer.parseInt(currentPageString);
        int recordsPerPage = recordsPerPageString == null ? 10 : Integer.parseInt(recordsPerPageString);
        String sortColumn = req.getParameter("sort_column");
        String searchField = req.getParameter("search_field");
        String searchParam = req.getParameter("search_param");

        RoomService roomService = new RoomService(
                req.getParameter("language") == null ?
                        req.getSession().getAttribute("language").toString() :
                        req.getParameter("language"));

        List<Room> rooms = roomService.getRoomList(
                currentPage,
                recordsPerPage,
                sortColumn != null ? sortColumn : new DBColumnManager().getProperty("room.id"),
                searchParam,
                searchField
        );

        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : rooms) {
            roomDtos.add(new RoomDto(room));
        }

        req.setAttribute("roomDtos", roomDtos);



        int rows = roomService.getNumberOfRooms(searchParam, searchField);

        int nOfPages = rows / recordsPerPage;
        nOfPages = rows % recordsPerPage > 0 ? ++nOfPages : nOfPages;

        currentPage = rows < currentPage * recordsPerPage && currentPage != 1 ? --currentPage : currentPage;

        req.setAttribute("sort_column", sortColumn);
        req.setAttribute("no_of_pages", nOfPages);
        req.setAttribute("current_page", currentPage);
        req.setAttribute("records_per_page", recordsPerPage);
        req.setAttribute("search_param", searchParam);
        req.setAttribute("search_field", searchField);
        req.setAttribute("search_fields", new ArrayList<>(Arrays.asList("number", "room type")));
        if(req.getParameter("user_id")!=null){
            req.setAttribute("user_id", req.getParameter("user_id"));
            req.setAttribute("reservation_id", req.getParameter("reservation_id"));
        }

        return manager.getProperty("path.page.room_management");
    }
}
