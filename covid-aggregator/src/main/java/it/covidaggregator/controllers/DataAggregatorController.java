package it.covidaggregator.controllers;

import it.covidaggregator.dto.Response;
import it.covidaggregator.dto.results.VaccinatedByAggregationDTO;
import it.covidaggregator.entities.CovidDocument;
import it.covidaggregator.services.DataAggregatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aggregator")
public class DataAggregatorController {

    private final DataAggregatorService dataAggregatorService;

    public DataAggregatorController(DataAggregatorService dataAggregatorService) {
        this.dataAggregatorService = dataAggregatorService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<Response<List<CovidDocument>>> findAll() {
        List<CovidDocument> list = dataAggregatorService.findAll();
        Response<List<CovidDocument>> response = Response.<List<CovidDocument>>builder().data(
                list
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/totalFirstDose")
    public ResponseEntity<Response<Integer>> findTotalFirstDose() {
        int count = dataAggregatorService.findTotalFirstDose();
        Response<Integer> response = Response.<Integer>builder().data(
                count
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/totalSecondDose")
    public ResponseEntity<Response<Integer>> findTotalSecondDose() {
        int count = dataAggregatorService.findTotalSecondDose();
        Response<Integer> response = Response.<Integer>builder().data(
                count
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/totalFirstDoseByProvinceCode")
    public ResponseEntity<Response<Integer>> findTotalFirstDoseByProvinceCode(@RequestParam() String provinceCode) {
        int count = dataAggregatorService.findTotalFirstDoseByProvinceCode(provinceCode);
        Response<Integer> response = Response.<Integer>builder().data(
                count
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/totalSecondDoseByProvinceCode")
    public ResponseEntity<Response<Integer>> findTotalSecondDoseByProvinceCode(@RequestParam() String provinceCode) {
        int count = dataAggregatorService.findTotalSecondDoseByProvinceCode(provinceCode);
        Response<Integer> response = Response.<Integer>builder().data(
                count
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/vaccinatedByProvinceCode")
    public ResponseEntity<Response<List<VaccinatedByAggregationDTO>>> findVaccinatedByProvinceCode(@RequestParam() String provinceCode) {
        List<VaccinatedByAggregationDTO> list = dataAggregatorService.findVaccinatedByProvinceCode(provinceCode);
        Response<List<VaccinatedByAggregationDTO>> response = Response.<List<VaccinatedByAggregationDTO>>builder().data(
                list
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/vaccinatedForMunicipalityByProvinceCode")
    public ResponseEntity<Response<List<VaccinatedByAggregationDTO>>> findVaccinatedForMunicipalityByProvinceCode(@RequestParam() String provinceCode) {
        List<VaccinatedByAggregationDTO> list = dataAggregatorService.findVaccinatedForMunicipalityByProvinceCode(provinceCode);
        Response<List<VaccinatedByAggregationDTO>> response = Response.<List<VaccinatedByAggregationDTO>>builder().data(
                list
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/municipalityWithMostSingleDoseVaccinations")
    public ResponseEntity<Response<CovidDocument>> findMunicipalityWithMostSingleDoseVaccinations() {
        CovidDocument list = dataAggregatorService.findMunicipalityWithMostSingleDoseVaccinations();
        Response<CovidDocument> response = Response.<CovidDocument>builder().data(
                list
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/municipalityWithMostDoubleDoseVaccinations")
    public ResponseEntity<Response<CovidDocument>> findMunicipalityWithMostDoubleDoseVaccinations() {
        CovidDocument list = dataAggregatorService.findMunicipalityWithMostDoubleDoseVaccinations();
        Response<CovidDocument> response = Response.<CovidDocument>builder().data(
                list
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/municipalityWithLeastSingleDoseVaccinations")
    public ResponseEntity<Response<CovidDocument>> findMunicipalityWithLeastSingleDoseVaccinations() {
        CovidDocument list = dataAggregatorService.findMunicipalityWithLeastSingleDoseVaccinations();
        Response<CovidDocument> response = Response.<CovidDocument>builder().data(
                list
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/municipalityWithLeastDoubleDoseVaccinations")
    public ResponseEntity<Response<CovidDocument>> findMunicipalityWithLeastDoubleDoseVaccinations() {
        CovidDocument list = dataAggregatorService.findMunicipalityWithLeastDoubleDoseVaccinations();
        Response<CovidDocument> response = Response.<CovidDocument>builder().data(
                list
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/municipalityWithMostSingleDoseVaccinationsByProvinceCode")
    public ResponseEntity<Response<CovidDocument>> findMunicipalityWithMostSingleDoseVaccinationsByProvinceCode(@RequestParam String provinceCode) {
        CovidDocument list = dataAggregatorService.findMunicipalityWithMostSingleDoseVaccinationsByProvinceCode(provinceCode);
        Response<CovidDocument> response = Response.<CovidDocument>builder().data(
                list
        ).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/municipalityWithMostDoubleDoseVaccinationsByProvinceCode")
    public ResponseEntity<Response<CovidDocument>> findMunicipalityWithMostDoubleDoseVaccinationsByProvinceCode(@RequestParam String provinceCode) {
        CovidDocument list = dataAggregatorService.findMunicipalityWithMostDoubleDoseVaccinationsByProvinceCode(provinceCode);
        Response<CovidDocument> response = Response.<CovidDocument>builder().data(
                list
        ).build();
        return ResponseEntity.ok(response);
    }

}
