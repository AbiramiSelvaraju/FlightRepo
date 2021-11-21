package com.demo.services;

import com.demo.config.Airline;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {

    private static final String TOPIC = "kafka_topic";

    @KafkaListener(topics = TOPIC, groupId="group_id", containerFactory = "userKafkaListenerFactory")

    public void consumeJson(Airline airline) {
        System.out.println("Consumed JSON Message: " + airline);
    }
    
}