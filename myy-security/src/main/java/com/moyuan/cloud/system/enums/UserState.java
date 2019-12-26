package com.moyuan.cloud.system.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author shuang.kou
 */

public enum UserState {
    CAN_USE("1"),
    CAN_NOT_USE("0");

    private String state;

    UserState(String state) {
        this.state = state;
    }

    @JsonCreator
    public static UserState fromRole(String state) {
        for (UserState type : UserState.values()) {
            if (type.getName().equals(state)) {
                return type;
            }
        }
        return null;
    }

    public String getName() {
        return this.state;
    }
}
