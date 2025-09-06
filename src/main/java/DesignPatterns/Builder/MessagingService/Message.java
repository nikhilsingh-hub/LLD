package DesignPatterns.Builder.MessagingService;

import DesignPatterns.Builder.WithBuilder;

@WithBuilder
public class Message {

    private MessageType messageType;
    private String content;
    private String sender;
    private String recipient;
    private boolean isDelivered;
    private long timestamp;

    public Message(MessageType messageType, String content, String sender, String recipient, boolean isDelivered, long timestamp) {
        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.isDelivered = isDelivered;
        this.timestamp = timestamp;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Static inner Builder class
    public static class Builder {
        private MessageType messageType;
        private String content;
        private String sender;
        private String recipient;
        private boolean isDelivered = false; // default value
        private long timestamp = System.currentTimeMillis(); // default to current time
        
        public Builder messageType(MessageType messageType) {
            this.messageType = messageType;
            return this;
        }
        
        public Builder content(String content) {
            this.content = content;
            return this;
        }
        
        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }
        
        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }
        
        public Builder isDelivered(boolean isDelivered) {
            this.isDelivered = isDelivered;
            return this;
        }
        
        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        
        public Message build() {
            // Validate required fields
            if (messageType == null) {
                throw new IllegalStateException("MessageType is required");
            }
            if (content == null || content.trim().isEmpty()) {
                throw new IllegalStateException("Content is required");
            }
            if (sender == null || sender.trim().isEmpty()) {
                throw new IllegalStateException("Sender is required");
            }
            if (recipient == null || recipient.trim().isEmpty()) {
                throw new IllegalStateException("Recipient is required");
            }
            
            return new Message(messageType, content, sender, recipient, isDelivered, timestamp);
        }
    }
}
