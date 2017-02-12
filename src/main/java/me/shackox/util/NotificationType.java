package me.shackox.util;

/**
 * Created by SHACKOX on 22/01/17.
 */
public enum NotificationType {
    NOTIFICATION("notification", "notification"),
    SUCCESS("success", "success"),
    INFORMATION("information", "info"),
    WARNING("warning", "warn"),
    ERROR("error", "error");

    private String completeName;
    private String shortName;

    NotificationType(String completeName, String shortName) {
        this.completeName = completeName;
        this.shortName = shortName;
    }

    public String getCompleteName() {
        return completeName;
    }

    public String getShortName() {
        return shortName;
    }
}
