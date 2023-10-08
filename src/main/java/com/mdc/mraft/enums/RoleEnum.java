package com.mdc.mraft.enums;

public enum RoleEnum {
    FOLLOWER(0, "Follower"), CANDIDATE(1, "Candidate"), LEADER(2, "Leader");

    private final int id;
    private final String name;

    RoleEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }
}
