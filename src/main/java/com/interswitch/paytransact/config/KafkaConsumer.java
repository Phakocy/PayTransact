package com.interswitch.paytransact.config;

import com.interswitch.paytransact.entities.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final String TOPIC = "Transaction_Topic";

    @KafkaListener(topics = TOPIC, containerFactory = "kafkaListenerJsonFactory", groupId = "group_id")
    public void consumeTopic(Transaction transaction) {
        System.out.println("**** -> Consumed Transactions:: {}" + transaction);
    }
}
