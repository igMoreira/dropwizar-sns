package com.my.example.services.aws;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.example.domain.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by isilva on 18/08/17.
 */
public class SqsProxy {

    private Map<String, String> queueTable = new HashMap<String, String>();
    private AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

    public Message sendMessage(Message msg, String queueName)
    {
        String queueUrl = getQueueUrl(queueName);
        try {
            SendMessageRequest sendMsgReq = new SendMessageRequest(queueUrl, MessageMapper.msgToJson(msg));
            sqs.sendMessage(sendMsgReq);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return msg;
    }


    public List<Message> receiveMessages(String queueName)
    {
        String queueUrl = getQueueUrl(queueName);
        List<com.amazonaws.services.sqs.model.Message> msgList = sqs.receiveMessage(queueUrl).getMessages();
        List<Message> parsedList = new ArrayList<Message>();

        for (com.amazonaws.services.sqs.model.Message msg : msgList)
        {
            try {
                parsedList.add(MessageMapper.jsonToMsg(msg.getBody()));
                sqs.deleteMessage(queueUrl, msg.getReceiptHandle());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return parsedList;
    }


    private String getQueueUrl(String queueName)
    {
        return (queueTable.containsKey(queueName)) ? queueTable.get(queueName) : createQueue(queueName);
    }

    private String createQueue(String queueName)
    {
        CreateQueueResult res = sqs.createQueue(queueName);
        queueTable.put(queueName, res.getQueueUrl());
        return queueTable.get(queueName);
    }

}
