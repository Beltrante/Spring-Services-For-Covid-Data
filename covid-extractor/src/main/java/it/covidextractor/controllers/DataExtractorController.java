package it.covidextractor.controllers;

import it.covidextractor.dto.Response;
import it.covidextractor.services.DataExtractorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping(path = "/extract")
public class DataExtractorController {

    private final DataExtractorService dataExtractorService;

    public DataExtractorController(DataExtractorService dataExtractorService) {
        this.dataExtractorService = dataExtractorService;
    }

    @GetMapping(path = "/get")
    public ResponseEntity<Response<String>> get() {
        Instant startTime = Instant.now();
        dataExtractorService.extractAndSendData(startTime);
        Response<String> response = Response.<String>builder().data(
                "Data extracted and sent via kafka successfully"
        ).build();
        return ResponseEntity.ok(response);
    }
}
