package org.lettsn.stoatWebhook;

import java.util.List;

import static org.lettsn.stoatWebhook.Utilities.formatWithQuotes;

// TODO: add builder
public class Message extends JsonCapable {
    // TODO: way to add attachments
    public List<String> attachments = List.of();
    public String content;
    public List<Embed> embeds;
    public NotificationType notificationType;
    public Interactions interactions = null;
    public Masquerade masquerade = null;
    private final String nonce = "";
    public List<Reply> replies = List.of();

    public Message(String content, List<Embed> embeds, NotificationType notificationType, List<Reply> replies) {
        this.content = content;
        this.embeds = embeds;
        this.notificationType = notificationType;
        this.replies = replies;
    }

    public Message(String content) {
        this.content = content;
        this.embeds = List.of();
        this.notificationType = NotificationType.SUPPRESS;
    }

    public Message(String content, List<Embed> embeds) {
        this.content = content;
        this.embeds = embeds;
        this.notificationType = NotificationType.SUPPRESS;
    }

    public Message(String content, NotificationType notificationType) {
        this.content = content;
        this.embeds = List.of();
        this.notificationType = notificationType;
    }

    @Override
    public String toJson() {
        StringBuilder embedString = new StringBuilder("[");
        for (Embed embed : this.embeds) {
            embedString.append(embed.toJson());
            embedString.append(",");
        }
        if (!this.embeds.isEmpty()) {
            embedString.deleteCharAt(embedString.length() - 1); // JSON doesn't support trailing commas.
        }
        embedString.append("]");

        StringBuilder replyString = new StringBuilder("[");
        for (Reply reply : this.replies) {
            replyString.append(reply.toJson());
            replyString.append(",");
        }
        if (!this.replies.isEmpty()) {
            replyString.deleteCharAt(replyString.length() - 1); // JSON doesn't support trailing commas.
        }
        replyString.append("]");

        String json = formatWithQuotes("{" +
                    "\"attachments\": []," +
                    "\"content\": %v," +
                    "\"embeds\": %s," +
                    "\"notification_type\": %d," +
                    "\"interactions\": null," +
                    "\"masquerade\": null," +
                    "\"nonce\": \"You should go watch Houseki no Kuni\"," +
                    "\"replies\": %s" +
                "}",
                this.content);

        return String.format(json, embedString, this.notificationType.getLevel(), replyString);
    }
}
