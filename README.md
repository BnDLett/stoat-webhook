# Stoat Webhook Library
A Java library designed to provide Stoat's webhook functionality.

# Adding to your project
## Gradle
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation "com.github.BnDLett:stoat-webhook:master-SNAPSHOT"
}
```

# Examples
## Basic "Hello, Phos!"
### Java
```java
static void sendMessage() {
    Webhook webhook = new Webhook("[YOUR WEBHOOK URL]");

    Message message = new Message("Hello, Phos!");
    int response = webhook.sendMessage(message);
    System.out.println(response);
}
```
