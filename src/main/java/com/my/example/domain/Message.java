package com.my.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by isilva on 18/08/17.
 */
public class Message {

    @JsonProperty
    @NotNull
    private UUID messageID;

    @JsonProperty
    @NotNull
    private String content;

    public UUID getMessageID() {
        return messageID;
    }

    public void setMessageID(UUID messageID) {
        this.messageID = messageID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
