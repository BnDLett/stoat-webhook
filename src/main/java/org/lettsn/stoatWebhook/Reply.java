package org.lettsn.stoatWebhook;

import static org.lettsn.stoatWebhook.Utilities.formatWithQuotes;

public class Reply extends JsonCapable {
    String id;
    boolean mention;
    /// Whether the reply should be allowed if there is an error. If true, the webhook/message will be sent regardless
    /// of errors in the Reply object.
    boolean allowError;

    public Reply(String id, boolean mention, boolean allowError) {
        this.id = id;
        this.mention = mention;
        this.allowError = allowError;
    }

    public String toJson() {
        return formatWithQuotes("{" +
                    "\"id\": %v," +
                    "\"mention\": %v," +
                    "\"allowError\": %v" +
                "}",
                this.id,
                String.valueOf(this.mention),
                String.valueOf(this.allowError));
    }
}
