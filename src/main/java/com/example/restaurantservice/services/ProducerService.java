package com.example.restaurantservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService
{
    private static final String TOPIC = "restaurant-topic";
    private static Logger logger = LoggerFactory.getLogger(ProducerService.class);
    @Autowired
    // Ignore the compiler's warning
    private KafkaTemplate<String, String> template;

    public void sendMessage(String message) {
        boolean disable = true;
        if (!disable) {
            template.send(TOPIC, message);
            logger.info("### Producer sends message [{}]", message);
            template.flush();
        }
    }
}
