package ua.training.controller.command;

import ua.training.controller.command.impl.*;

public enum CommandEnum {
    DEFAULT(new DefaultCommand()),//TODO
    LOGIN_PAGE(new LoginPageCommand()),
    REGISTER_PAGE(new RegisterPageCommand()),
    BOOKING_PAGE(new BookingPageCommand()),//TODO
    PROFILE_PAGE(new ProfilePageCommand()),
    BOOK_MANAGEMENT_PAGE(new BookManagementPageCommand()),//TODO
    ROOM_MANAGEMENT_PAGE(new RoomManagementPageCommand()),
    CONFIRMATION_PAGE(new ConfirmationPageCommand()),//TODO
    USER_MANAGEMENT_PAGE(new UserManagementPageCommand()),
    SESSION_MANAGEMENT_PAGE(new SessionManagementPageCommand()),
    EDIT_USER_PAGE(new EditUserPageCommand()),
    VIEW_USER_PAGE(new ViewUserPageCommand()),
    EDIT_ROOM_PAGE(new EditRoomPageCommand()),//TODO
    CREATE_ROOM_PAGE(new CreateRoomPageCommand()),//TODO
    EDIT_ROOM_TYPE_PAGE(new EditRoomTypePageCommand()),//TODO
    CREATE_ROOM_TYPE_PAGE(new CreateRoomTypePageCommand()),//TODO
    USER_BOOKINGS_PAGE(new UserBookingsPageCommand()),//TODO
    EDIT_ROOM(new EditRoomCommand()),//TODO
    CREATE_ROOM(new CreateRoomCommand()),//TODO
    DELETE_ROOM(new DeleteRoomCommand()),//TODO
    EDIT_ROOM_TYPE(new EditRoomTypeCommand()),//TODO
    CREATE_ROOM_TYPE(new CreateRoonTypeCommand()),//TODO
    DELETE_ROOM_TYPE(new DeleteRoomTypeCommand()),//TODO
    CREATE_CONFIRMATION(new CreateConfirmationCommand()),//TODO
    DELETE_CONFIRMATION(new DeleteConfirmationCommand()),//TODO
    PROFILE_UPDATE(new ProfileUpdateCommand()),
    EDIT_USER(new EditUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand()),
    LOGIN(new LoginCommand());

    Command command;
    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
