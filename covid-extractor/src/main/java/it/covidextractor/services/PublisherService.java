package it.covidextractor.services;

import it.covidextractor.dto.CovidKafkaMessage;

import java.util.List;

public interface PublisherService {
    /**
     * Publishes a single {@link CovidKafkaMessage} to the Kafka topic.
     * <p>
     * This method sends the provided {@link CovidKafkaMessage} to the Kafka topic configured in the
     * application properties.
     *
     * @param message The {@link CovidKafkaMessage} object to be published.
     */
    void publish(CovidKafkaMessage message);

    /**
     * Publishes a list of {@link CovidKafkaMessage} objects to the Kafka topic.
     * <p>
     * This method iterates over the list of messages and publishes each one individually to the
     * Kafka topic.
     *
     * @param messages A list of {@link CovidKafkaMessage} objects to be published.
     */
    void publishAll(List<CovidKafkaMessage> messages);
}
