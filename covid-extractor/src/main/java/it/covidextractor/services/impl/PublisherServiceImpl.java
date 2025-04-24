package it.covidextractor.services.impl;

import it.covidextractor.dto.CovidKafkaMessage;
import it.covidextractor.services.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value(value = "${kafka.covid.topic}")
    private String topic;

    @Autowired
    public PublisherServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(CovidKafkaMessage message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    public void publishAll(List<CovidKafkaMessage> messages) {
        for (CovidKafkaMessage message : messages) {
            this.publish(message);
        }
    }
}
