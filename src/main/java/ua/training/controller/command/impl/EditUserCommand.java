package ua.training.controller.command.impl;

import ua.training.controller.command.Command;
import ua.training.controller.utils.RegexpUtil;
import ua.training.controller.utils.UserUtil;
import ua.training.model.entity.User;
import ua.training.model.exception.UnsuccessfulSqlOperationException;
import ua.training.model.service.UserService;
import ua.training.model.service.resourceManager.MessageManager;
import ua.training.model.service.resourceManager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EditUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if (!new UserUtil().validateRole(
                User.Role.ADMIN,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )) {
            return "redirect:/app/default";
        }

        Locale locale = new Locale(req.getParameter("language") == null ?
                req.getSession().getAttribute("language").toString() :
                req.getParameter("language"));

        String old_username = req.getParameter("old_username"),
                username = req.getParameter("username"),
                password = req.getParameter("password"),
                name = req.getParameter("name"),
                surname = req.getParameter("surname"),
                phone_number = req.getParameter("phone_number"),
                old_email = req.getParameter("old_email"),
                email = req.getParameter("email"),
                role = req.getParameter("role"),
                money = req.getParameter("money");

        RegexpUtil regexUtil = new RegexpUtil(locale);

        Map<String, String> errorMap = new HashMap<>();

        UserService userService = new UserService();

        regexUtil.validate(errorMap, old_username, "regexp.username","username");
        regexUtil.validate(errorMap, username, "regexp.username","username");
        regexUtil.validate(errorMap, password, "regexp.password","password");
        regexUtil.validate(errorMap, name, "regexp.name","name");
        regexUtil.validate(errorMap, surname, "regexp.surname","surname");
        regexUtil.validate(errorMap, phone_number, "regexp.phone_number","phone_number");
        regexUtil.validate(errorMap, old_email, "regexp.email","email");
        regexUtil.validate(errorMap, email, "regexp.email","email");
        regexUtil.validate(errorMap, money, "regexp.money","money");

        try {
            User.Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e){
            errorMap.put("wrongrole",new MessageManager(locale).getProperty("wrong.role"));
        }

        if(!old_username.equals(username)&&userService.checkLogin(username)){
            errorMap.put("existusername",new MessageManager(locale).getProperty("exist.username"));
        }
        if(!old_username.equals(username)&&userService.checkLogin(username)){
            errorMap.put("existemail",new MessageManager(locale).getProperty("exist.email"));
        }

        if (errorMap.size() > 0) {
            for (String error : errorMap.keySet()) {
                req.setAttribute(error, errorMap.get(error));
            }
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("name",name);
            req.setAttribute("surname",surname);
            req.setAttribute("phone_number",phone_number);
            req.setAttribute("email",email);
            req.setAttribute("role",userService.getUserRole(old_username).name().toLowerCase());
            req.setAttribute("money",money);
            return new PagePathManager().getProperty("path.page.profile");
        }

        try {
            userService.updateUser(new User.Builder()
                            .buildUsername(username)
                            .buildPassword(password)
                            .buildName(name)
                            .buildSurname(surname)
                            .buildEmail(email)
                            .buildPhone(phone_number)
                            .buildMoney((long) (Double.valueOf(money)*100))
                            .buildRole(User.Role.valueOf(role.toUpperCase()))
                            .getUser(),
                    old_username
            );
            Map<String, HttpSession> sessionMap = (Map<String, HttpSession>)req.getSession().getServletContext().getAttribute("sessions");
            sessionMap.remove(old_username);
            req.getSession().setAttribute("username",username);
            sessionMap.put(username,req.getSession());
        } catch (UnsuccessfulSqlOperationException e){
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("name",name);
            req.setAttribute("surname",surname);
            req.setAttribute("phone_number",phone_number);
            req.setAttribute("email",email);
            req.setAttribute("role",userService.getUserRole(old_username).name().toLowerCase());
            req.setAttribute("money",money);
            return new PagePathManager().getProperty("path.page.profile");
        }
        return "redirect:/app/user_management_page";
    }
}
