package com.pesto.tech.data.entity;

import lombok.Getter;

@Getter
public enum TaskStatus {
    ALL("All"),
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

}
