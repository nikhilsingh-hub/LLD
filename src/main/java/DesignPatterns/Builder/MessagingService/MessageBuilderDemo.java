package DesignPatterns.Builder.MessagingService;

public class MessageBuilderDemo {
    
    public static void main(String[] args) {

        Message textMessage = new Message.Builder()
                .messageType(MessageType.TEXT)
                .content("Hello, how are you?")
                .sender("alice@example.com")
                .recipient("bob@example.com")
                .isDelivered(false)
                .timestamp(System.currentTimeMillis())
                .build();
    }
} 