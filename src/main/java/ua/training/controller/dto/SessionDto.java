package ua.training.controller.dto;

import javax.servlet.http.HttpSession;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class SessionDto {
    private HttpSession session;
    private String username,
            sessionId,
            creationTime,
            lastActivity,
            language,
            role;

    public SessionDto(HttpSession session, String language) {
        this.session = session;
        this.language = session.getAttribute("language").toString();
        username = session.getAttribute("username").toString();
        sessionId = session.getId();
        role = session.getAttribute("role").toString();
        creationTime = ZonedDateTime.of(
                Instant.ofEpochMilli(session.getCreationTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                ZoneId.systemDefault()
        ).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(new Locale(language.replaceAll("_.*",""))));
        lastActivity = ZonedDateTime.of(
                Instant.ofEpochMilli(session.getLastAccessedTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                ZoneId.systemDefault()
        ).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(new Locale(language.replaceAll("_.*",""))));
    }

    public HttpSession getSession() {
        return session;
    }

    public String getUsername() {
        return username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getRole() {
        return role;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getLastActivity() {
        return lastActivity;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public void setLastActivity(String lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
