package org.lettsn.stoatWebhook;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import static org.lettsn.stoatWebhook.Utilities.formatWithQuotes;

public class Embed extends JsonCapable {
    public String colour;
    public String description;
    public URI icon;
    public String media = null;
    public String title;
    public URI url;

    public Embed(EmbedBuilder builder) {
        this.colour = builder.colour;
        this.description = builder.description;;
        this.icon = builder.icon;
        this.media = builder.media;
        this.title = builder.title;
        this.url = builder.url;
    }

    // TODO: implement media method.
    public static class EmbedBuilder extends Embed {
        // https://regexbox.com/regex-templates/hex-color
        static Pattern colourPattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

        private EmbedBuilder(EmbedBuilder builder) {
            super(builder);
        }

        private void ensureSize(String content, int max, String message) {
            if (content.length() > max || content.isEmpty()) {
                throw new IllegalArgumentException(message);
            }
        }

        public EmbedBuilder colour(String hex) {
            if (!colourPattern.matcher(hex).matches()) {
                throw new IllegalArgumentException("Provided hex color is not valid.");
            }

            this.colour = hex;
            return this;
        }

        public EmbedBuilder description(String content) {
            ensureSize(content, 2000, "Description cannot be empty and cannot exceed 2000 characters.");

            this.description = content;
            return this;
        }

        public EmbedBuilder icon(URI uri) {
            ensureSize(uri.toString(), 256, "URI cannot exceed 256 characters.");
            this.icon = uri;
            return this;
        }

        public EmbedBuilder icon(String uri) throws URISyntaxException {
            ensureSize(uri, 256, "URI cannot be empty or exceed 256 characters.");
            this.icon = new URI(uri);
            return this;
        }

        public EmbedBuilder title(String title) {
            ensureSize(title, 100, "Title cannot be empty or larger than 100 characters.");

            this.title = title;
            return this;
        }

        public EmbedBuilder url(URI url) {
            ensureSize(url.toString(), 256, "URL cannot be larger than 256 characters.");
            this.url = url;
            return this;
        }

        public EmbedBuilder url(String url) throws URISyntaxException {
            ensureSize(url, 256, "URL cannot be empty or larger than 256 characters.");
            this.url = new URI(url);
            return this;
        }

        public Embed build() {
            return new Embed(this);
        }
    }

    public String toJson() {
        return formatWithQuotes(
                "{" +
                    "\"colour\": %v," +
                    "\"description\": %v," +
                    "\"icon\": %v," +
                    "\"media\": %v," +
                    "\"title\": %v," +
                    "\"url\": %v," +
                "}",
                this.colour,
                this.description,
                this.icon,
                this.media,
                this.title,
                this.url);
    }
}
