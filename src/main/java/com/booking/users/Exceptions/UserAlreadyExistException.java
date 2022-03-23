package com.booking.users.Exceptions;

public class UserAlreadyExistException extends Throwable {
    public UserAlreadyExistException(String user_already_exists) {
        return;
    }

    public String message() {
        return "User Already exists";
    }
}
