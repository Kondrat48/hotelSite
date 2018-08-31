package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class ProfilePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if(!new UserUtil().validateRole(
                User.Role.BANNED,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )){
            return "redirect:"+manager.getProperty("path.page.index");
        }
        UserService service = new UserService();
        User user = service.getUser((String) req.getSession().getAttribute("username"));
        req.setAttribute("username",user.getUsername());
        req.setAttribute("name",user.getName());
        req.setAttribute("surname",user.getSurname());
        req.setAttribute("phone_number",user.getPhoneNumber());
        req.setAttribute("email",user.getEmail());
        req.setAttribute("role",user.getRole().name().toLowerCase());
        req.setAttribute("money",user.getMoney()/100d);

        return manager.getProperty("path.page.profile");
    }
}
