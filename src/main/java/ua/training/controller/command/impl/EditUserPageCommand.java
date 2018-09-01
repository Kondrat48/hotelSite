package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.RegexpUtil;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.resourceManager.PagePathManager;
import ua.training.model.service.resourceManager.RegexManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class EditUserPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if(!new UserUtil().validateRole(
                User.Role.ADMIN,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )){
            return "redirect:/app/default";
        }

        UserService userService = new UserService();
        String oldUsername = req.getParameter("username");
        if(oldUsername!=null&&
                oldUsername.matches(new RegexManager().getProperty("regexp.username"))&&
                userService.checkLogin(oldUsername)
        ){
            User user = userService.getUser(oldUsername);
            req.setAttribute("username",user.getUsername());
            req.setAttribute("password",user.getPassword());
            req.setAttribute("name",user.getName());
            req.setAttribute("surname",user.getSurname());
            req.setAttribute("phone_number",user.getPhoneNumber());
            req.setAttribute("email",user.getEmail());
            req.setAttribute("role",user.getRole().name().toLowerCase());
            req.setAttribute("money",user.getMoney()/100d);
            return manager.getProperty("path.page.edit_user");
        }
        return "redirect:/app/default";
    }
}
