package com.moyuan.cloud.system.exception;

/**
 * @author shuang.kou
 */
public class UserNameAlreadyExistException extends RuntimeException {
    public UserNameAlreadyExistException() {
    }

    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}
