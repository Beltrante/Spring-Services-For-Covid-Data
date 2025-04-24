package it.covidaggregator.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.covidaggregator.dto.CovidKafkaMessage;
import it.covidaggregator.services.ConsumerService;
import it.covidaggregator.services.DataAggregatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final DataAggregatorService dataAggregatorService;

    public ConsumerServiceImpl(DataAggregatorService dataAggregatorService) {
        this.dataAggregatorService = dataAggregatorService;
    }


    @Override
    @KafkaListener(topics = "${kafka.covid.topic}", groupId = "${kafka.covid.group_id}")
    public void consume(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CovidKafkaMessage kMessage = mapper.readValue(message, CovidKafkaMessage.class);
            dataAggregatorService.store(kMessage);
        } catch (JsonProcessingException e) {
            log.error("Error in parsing json", e);
        }
    }
}
