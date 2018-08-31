package ua.training.controller.utils;

import ua.training.model.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

public class UserUtil {
    private User.Role getPageRole(String uri) {
        for (User.Role role : User.Role.values()) {
            if (uri.contains(role.name().toLowerCase())) {
                return role;
            }
        }
        return User.Role.GUEST;
    }

    public boolean validateUri(String role, String uri) {
        return User.Role.valueOf(role.toUpperCase()).getSignificance()>=getPageRole(uri).getSignificance();
    }

    public boolean validateRole(User.Role required, User.Role current){
        return current.getSignificance()>=required.getSignificance();
    }

    public String getRandomUniqueUsername(HttpSession session){
        String username = "guest"+new Random().nextInt(99998)+1;
        if(!((Map<String, HttpSession>)session.getServletContext().getAttribute("sessions")).keySet().contains(username)){
          return username;
        }
        return getRandomUniqueUsername(session);
    }
}
