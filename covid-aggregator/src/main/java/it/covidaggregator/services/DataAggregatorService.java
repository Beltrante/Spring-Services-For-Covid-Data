package it.covidaggregator.services;

import it.covidaggregator.dto.CovidKafkaMessage;
import it.covidaggregator.dto.results.VaccinatedByAggregationDTO;
import it.covidaggregator.entities.CovidDocument;
import it.covidaggregator.exceptions.MissingProvinceCodeException;

import java.util.List;

public interface DataAggregatorService {
    void store(CovidKafkaMessage message);

    List<CovidDocument> findAll();


    int findTotalFirstDose();


    int findTotalSecondDose();


    int findTotalFirstDoseByProvinceCode(String provinceCode) throws MissingProvinceCodeException;


    int findTotalSecondDoseByProvinceCode(String provinceCode) throws MissingProvinceCodeException;


    List<VaccinatedByAggregationDTO> findVaccinatedByProvinceCode(String provinceCode);


    List<VaccinatedByAggregationDTO> findVaccinatedForMunicipalityByProvinceCode(String provinceCode);


    CovidDocument findMunicipalityWithMostSingleDoseVaccinations();


    CovidDocument findMunicipalityWithMostDoubleDoseVaccinations();


    CovidDocument findMunicipalityWithLeastSingleDoseVaccinations();


    CovidDocument findMunicipalityWithLeastDoubleDoseVaccinations();


    CovidDocument findMunicipalityWithMostSingleDoseVaccinationsByProvinceCode(String provinceCode) throws MissingProvinceCodeException;


    CovidDocument findMunicipalityWithMostDoubleDoseVaccinationsByProvinceCode(String provinceCode) throws MissingProvinceCodeException;
}
