package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.RegexpUtil;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.resourceManager.MessageManager;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        if (!req.getSession().getAttribute("role").equals(User.Role.GUEST.name().toLowerCase())) {
            return "redirect:" + new PagePathManager().getProperty("path.page.index");
        }

        Locale locale = new Locale((String) req.getSession().getAttribute("language"));
        MessageManager messageManager = new MessageManager(locale);
        RegexpUtil regexUtil = new RegexpUtil(locale);
        UserService userService = new UserService();

        Map<String, String> errorMap = new HashMap<>();

        String username = req.getParameter("username"),
                password = req.getParameter("password"),
                name = req.getParameter("name"),
                surname = req.getParameter("surname"),
                phone_number = req.getParameter("phone_number"),
                email = req.getParameter("email");

        regexUtil.validate(errorMap, username, "regexp.username","username");
        regexUtil.validate(errorMap, password, "regexp.password","password");
        regexUtil.validate(errorMap, name, "regexp.name","name");
        regexUtil.validate(errorMap, surname, "regexp.surname","surname");
        regexUtil.validate(errorMap, phone_number, "regexp.phone_number","phone_number");
        regexUtil.validate(errorMap, email, "regexp.email","email");
        if (errorMap.size() == 0) {
            if (userService.checkLogin(username)) {
                errorMap.put("existusername", messageManager.getProperty("exist.username"));
            }
            if (userService.checkEmail(email)) {
                errorMap.put("existemail", messageManager.getProperty("exist.email"));
            }
        }

        if(username!=null&&password!=null&&email!=null&&errorMap.size()==0){
            userService.register(new User.Builder()
                    .buildUsername(username)
                    .buildPassword(password)
                    .buildName(name)
                    .buildSurname(surname)
                    .buildPhone(phone_number)
                    .buildEmail(email)
                    .getUser()
            );
            return "redirect:" + new PagePathManager().getProperty("path.page.login");
        }
        for (String error : errorMap.keySet()) {
            req.setAttribute(error, errorMap.get(error));
        }
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.setAttribute("name",name);
        req.setAttribute("surname",surname);
        req.setAttribute("phone_number",phone_number);
        req.setAttribute("email",email);

        return new PagePathManager().getProperty("path.page.register");

    }
}