package it.covidextractor.services.impl;

import it.covidextractor.dto.CovidKafkaMessage;
import it.covidextractor.dto.CovidData;
import it.covidextractor.dto.mappers.CovidKafkaMessageMapper;
import it.covidextractor.entities.Province;
import it.covidextractor.exceptions.types.CovidDataSourceException;
import it.covidextractor.repositories.ProvinceRepository;
import it.covidextractor.services.DataExtractorService;
import it.covidextractor.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;

@Service
public class DataExtractorServiceImpl implements DataExtractorService {

    @Value("${covid.data.url}")
    private String covidDataUrl;

    private final ProvinceRepository provinceRepository;
    private final CovidKafkaMessageMapper covidKafkaMessageMapper;
    private final PublisherService publisherService;

    @Autowired
    public DataExtractorServiceImpl(ProvinceRepository provinceRepository, CovidKafkaMessageMapper covidKafkaMessageMapper, PublisherService publisherService) {
        this.provinceRepository = provinceRepository;
        this.covidKafkaMessageMapper = covidKafkaMessageMapper;
        this.publisherService = publisherService;
    }

    @Override
    public void extractAndSendData(Instant now) {
        List<CovidData> data = loadFromUrl();
        List<CovidKafkaMessage> kafkaData = addProvinceAndTimeData(data, now);
        publisherService.publishAll(kafkaData);
    }

    @Override
    public List<CovidData> loadFromUrl() throws CovidDataSourceException {
        CovidData[] covidArray;
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CovidData[]> responseEntity =
                    restTemplate.getForEntity(covidDataUrl, CovidData[].class);
            covidArray = responseEntity.getBody();
        } catch (Exception e) {
            throw new CovidDataSourceException("Error while parsing data from" + covidDataUrl);
        }
        if (covidArray == null || covidArray.length == 0) {
            throw new CovidDataSourceException("No data found at: " + covidDataUrl);
        }
        return Arrays.stream(covidArray).toList();
    }

    @Override
    public List<CovidKafkaMessage> addProvinceAndTimeData(List<CovidData> data, Instant now) {
        List<Province> provinceList = provinceRepository.findAll();
        return covidKafkaMessageMapper.covidListToKafkaMessageList(
                data,
                provinceList,
                now.toString()
        );
    }
}
