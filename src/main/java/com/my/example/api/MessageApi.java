package com.my.example.api;

import com.my.example.domain.Message;
import com.my.example.services.aws.SqsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by isilva on 18/08/17.
 */
@Path("/msg")
@Consumes(MediaType.APPLICATION_JSON)
public class MessageApi {

    private final Logger LOG = LoggerFactory.getLogger(MessageApi.class);
    private final SqsProxy service = new SqsProxy();
    private final String QUEUE_NAME;

    public MessageApi(String queueName) {
        QUEUE_NAME = queueName;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Message sendMessage(Message msg){
        service.sendMessage(msg, QUEUE_NAME);
        return msg;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAllMessages(Message msg)
    {
        return service.receiveMessages(QUEUE_NAME);
    }

}
