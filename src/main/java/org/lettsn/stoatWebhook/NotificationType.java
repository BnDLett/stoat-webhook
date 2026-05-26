package org.lettsn.stoatWebhook;

public enum NotificationType {
    /// Suppress all notifications.
    SUPPRESS(0),
    /// Mention everyone.
    EVERYONE(1),
    /// Mention online.
    ONLINE(2);

    public final int level;

    NotificationType(int i) {
        this.level = i;
    }

    public int getLevel() {
        return this.level;
    }
}
