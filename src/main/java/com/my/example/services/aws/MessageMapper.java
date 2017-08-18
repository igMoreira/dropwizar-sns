package com.my.example.services.aws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.example.domain.Message;

import java.io.IOException;

/**
 * Created by isilva on 18/08/17.
 */
public class MessageMapper {

    public static String msgToJson(Message msg) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(msg);
    }

    public static Message jsonToMsg(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Message.class);
    }
}
