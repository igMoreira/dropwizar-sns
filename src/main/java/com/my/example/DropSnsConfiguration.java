package com.my.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

/**
 * Created by isilva on 18/08/17.
 */
public class DropSnsConfiguration extends Configuration{

    @JsonProperty("environment")
    private String env;

    @JsonProperty
    private String queueName;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}
