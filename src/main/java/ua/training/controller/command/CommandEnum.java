package ua.training.controller.command;

import ua.training.controller.command.impl.*;

public enum CommandEnum {
    DEFAULT(new DefaultCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    REGISTER_PAGE(new RegisterPageCommand()),
    BOOKING_PAGE(new BookingPageCommand()),
    PROFILE_PAGE(new ProfilePageCommand()),
    BOOK_MANAGEMENT_PAGE(new BookManagementPageCommand()),
    ROOM_MANAGEMENT_PAGE(new RoomManagementPageCommand()),
    CONFIRMATION_PAGE(new ConfirmationPageCommand()),
    USER_MANAGEMENT_PAGE(new UserManagementPageCommand()),
    SESSION_MANAGEMENT_PAGE(new SessionManagementPageCommand()),
    EDIT_USER_PAGE(new EditUserPageCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand()),
    PROFILE_UPDATE(new ProfileUpdateCommand()),
    LOGIN(new LoginCommand());

    Command command;
    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
