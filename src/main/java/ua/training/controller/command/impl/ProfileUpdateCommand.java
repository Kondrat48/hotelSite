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

public class ProfileUpdateCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        PagePathManager manager = new PagePathManager();
        if (!new UserUtil().validateRole(
                User.Role.BANNED,
                User.Role.valueOf(((String) req.getSession().getAttribute("role")).toUpperCase())
        )) {
            return "redirect:" + manager.getProperty("path.page.index");
        }


        Locale locale = new Locale((String) req.getSession().getAttribute("language"));
        MessageManager messageManager = new MessageManager(locale);
        RegexpUtil regexUtil = new RegexpUtil(locale);
        UserService userService = new UserService();

        Map<String, String> errorMap = new HashMap<>();


        String old_username = (String) req.getSession().getAttribute("username"),
                username = req.getParameter("username"),
                old_password = req.getParameter("old_password"),
                new_password = req.getParameter("new_password"),
                name = req.getParameter("name"),
                surname = req.getParameter("surname"),
                phone_number = req.getParameter("phone_number"),
                email = req.getParameter("email"),
                old_email = req.getParameter("old_email"),
                money = req.getParameter("money");


        regexUtil.validate(errorMap, username, "regexp.username", "username");
        regexUtil.validate(errorMap, old_password, "regexp.password", "password");
        regexUtil.validate(errorMap, new_password, "regexp.password", "password");
        regexUtil.validate(errorMap, name, "regexp.name", "name");
        regexUtil.validate(errorMap, surname, "regexp.surname", "surname");
        regexUtil.validate(errorMap, phone_number, "regexp.phone_number", "phone_number");
        regexUtil.validate(errorMap, email, "regexp.email", "email");
        regexUtil.validate(errorMap, old_email, "regexp.email", "email");
        regexUtil.validate(errorMap, money, "regexp.money", "money");
        if (errorMap.size() == 0) {
            if (!userService.getUser(username).getPassword().equals(old_password)) {
                errorMap.put("errorold_password", messageManager.getProperty("incorrect.password"));
            }
            if (!username.equals(old_username) && userService.checkLogin(username)) {
                errorMap.put("exist" + username, messageManager.getProperty("exist." + username));
            }
            if (!old_email.equals(email) && userService.checkEmail(email)) {
                errorMap.put("exist" + email, messageManager.getProperty("exist." + email));
            }

        }

        if (errorMap.size() > 0) {
            for (String error : errorMap.keySet()) {
                req.setAttribute(error, errorMap.get(error));
            }
            req.setAttribute("username", username);
            req.setAttribute("name", name);
            req.setAttribute("surname", surname);
            req.setAttribute("phone_number", phone_number);
            req.setAttribute("email", email);
            req.setAttribute("role", userService.getUserRole(old_username).name().toLowerCase());
            req.setAttribute("money", money);
            return new PagePathManager().getProperty("path.page.profile");
        }

        try {
            userService.updateUser(new User.Builder()
                            .buildUsername(username)
                            .buildPassword(new_password)
                            .buildName(name)
                            .buildSurname(surname)
                            .buildEmail(email)
                            .buildPhone(phone_number)
                            .buildMoney((long) (Double.valueOf(money) * 100))
                            .getUser(),
                    old_username
            );
            Map<String, HttpSession> sessionMap = (Map<String, HttpSession>) req.getSession().getServletContext().getAttribute("sessions");
            sessionMap.remove(old_username);
            req.getSession().setAttribute("username", username);
            sessionMap.put(username, req.getSession());
        } catch (UnsuccessfulSqlOperationException e) {
            req.setAttribute("username", username);
            req.setAttribute("name", name);
            req.setAttribute("surname", surname);
            req.setAttribute("phone_number", phone_number);
            req.setAttribute("email", email);
            req.setAttribute("role", userService.getUserRole(old_username).name().toLowerCase());
            req.setAttribute("money", money);
            return new PagePathManager().getProperty("path.page.profile");
        }


        return "redirect:/app/profile_page";
    }
}
