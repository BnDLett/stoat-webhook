package org.lettsn.stoatWebhook;

class Utilities {
    public static String withQuotes(String value) {
        if (value == null) { return null; }

        return String.format("\"%s\"", value);
    }

    @SafeVarargs
    public static <T> String formatWithQuotes(String template, T... values) {
        String result = template;

        for (T value : values) {
            result = result.replaceFirst("%v", String.format("\"%s\"", value));
        }

        return result;
    }
}
