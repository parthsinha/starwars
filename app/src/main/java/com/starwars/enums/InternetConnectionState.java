package com.starwars.enums;

public enum InternetConnectionState {
    UNKNOWN(0, "Not yet known"),
    AVAILABLE(1, "Available"),
    UNAVAILABLE(2, "Not available"),
    WAITING_FOR_RECONNECT(3, "Waiting for reconnect");

    private final int ordinal;
    private final String friendlyString;

    InternetConnectionState(int ordinal, String friendlyString) {
        this.ordinal = ordinal;
        this.friendlyString = friendlyString;
    }

    public String getFriendlyString() {
        return friendlyString;
    }

    public int getOrdinal() {
        return ordinal;
    }
}
