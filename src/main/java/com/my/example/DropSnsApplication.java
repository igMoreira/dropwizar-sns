package com.my.example;

import com.my.example.api.MessageApi;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by isilva on 18/08/17.
 */
public class DropSnsApplication extends Application<DropSnsConfiguration> {

    private final Logger LOG = LoggerFactory.getLogger(DropSnsApplication.class);

    public static void main(String[] args) throws Exception {
        new DropSnsApplication().run(args);
    }

    public void run(DropSnsConfiguration config, Environment environment) throws Exception {
        LOG.debug("Registering app resources...");

        environment.jersey().register(new MessageApi(config.getQueueName()));

        LOG.debug("All resources have been registered successfully");
    }
}
