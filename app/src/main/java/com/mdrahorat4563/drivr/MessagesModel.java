package com.mdrahorat4563.drivr;

/**
 * Created by Michal Drahorat on 10/18/2017.
 */

public class MessagesModel {
    private int messageId;
    private String messageText;
    private int senderId;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public MessagesModel(int messageId, String messageText, int senderId) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.senderId = senderId;
    }


}
