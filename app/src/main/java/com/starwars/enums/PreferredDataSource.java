package com.starwars.enums;

public enum PreferredDataSource {
    UNKNOWN(0, "Not yet known"),
    SERVICES(1, "Using services"),
    DATABASE(2, "Using database");

    private final String friendlyString;

    PreferredDataSource(int ordinal, String friendlyString) {
        this.friendlyString = friendlyString;
    }

    public String getFriendlyString() {
        return friendlyString;
    }
}
