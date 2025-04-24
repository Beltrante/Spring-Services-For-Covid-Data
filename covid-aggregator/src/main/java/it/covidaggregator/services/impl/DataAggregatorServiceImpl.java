package it.covidaggregator.services.impl;

import it.covidaggregator.dto.CovidKafkaMessage;
import it.covidaggregator.dto.mappers.CovidDataMapper;
import it.covidaggregator.dto.results.VaccinatedByAggregationDTO;
import it.covidaggregator.entities.CovidDocument;
import it.covidaggregator.exceptions.MissingProvinceCodeException;
import it.covidaggregator.repositories.DataAggregatorRepository;
import it.covidaggregator.services.DataAggregatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataAggregatorServiceImpl implements DataAggregatorService {

    private final DataAggregatorRepository dataAggregatorRepository;
    private final CovidDataMapper covidDataMapper;

    public DataAggregatorServiceImpl(DataAggregatorRepository dataAggregatorRepository, CovidDataMapper covidDataMapper) {
        this.dataAggregatorRepository = dataAggregatorRepository;
        this.covidDataMapper = covidDataMapper;
    }


    @Override
    public void store(CovidKafkaMessage message) {
        CovidDocument item = covidDataMapper.dtoToEntity(message);
        dataAggregatorRepository.save(item);
    }

    @Override
    public List<CovidDocument> findAll() {
        return dataAggregatorRepository.findAll();
    }

    @Override
    public int findTotalFirstDose() {
        return dataAggregatorRepository.findTotalFirstDose();
    }

    @Override
    public int findTotalSecondDose() {
        return dataAggregatorRepository.findTotalSecondDose();
    }

    @Override
    public int findTotalFirstDoseByProvinceCode(String provinceCode) throws MissingProvinceCodeException {
        Optional<Integer> res = dataAggregatorRepository.findTotalFirstDoseByProvinceCode(provinceCode);
        if (res.isPresent()) {
            return res.get();
        } else {
            throw new MissingProvinceCodeException("Province code not found");
        }
    }

    @Override
    public int findTotalSecondDoseByProvinceCode(String provinceCode) throws MissingProvinceCodeException {
        Optional<Integer> res = dataAggregatorRepository.findTotalSecondDoseByProvinceCode(provinceCode);
        if (res.isPresent()) {
            return res.get();
        } else {
            throw new MissingProvinceCodeException("Province code not found");
        }
    }

    @Override
    public List<VaccinatedByAggregationDTO> findVaccinatedByProvinceCode(String provinceCode) {
        return dataAggregatorRepository.findVaccinatedByProvinceCode(provinceCode);
    }

    @Override
    public List<VaccinatedByAggregationDTO> findVaccinatedForMunicipalityByProvinceCode(String provinceCode) {
        return dataAggregatorRepository.findVaccinatedForMunicipalityByProvinceCode(provinceCode);
    }

    @Override
    public CovidDocument findMunicipalityWithMostSingleDoseVaccinations() {
        return dataAggregatorRepository.findMunicipalityWithMostSingleDoseVaccinations();
    }

    @Override
    public CovidDocument findMunicipalityWithMostDoubleDoseVaccinations() {
        return dataAggregatorRepository.findMunicipalityWithMostDoubleDoseVaccinations();
    }

    @Override
    public CovidDocument findMunicipalityWithLeastSingleDoseVaccinations() {
        return dataAggregatorRepository.findMunicipalityWithLeastSingleDoseVaccinations();
    }

    @Override
    public CovidDocument findMunicipalityWithLeastDoubleDoseVaccinations() {
        return dataAggregatorRepository.findMunicipalityWithLeastDoubleDoseVaccinations();
    }

    @Override
    public CovidDocument findMunicipalityWithMostSingleDoseVaccinationsByProvinceCode(String provinceCode) throws MissingProvinceCodeException {
        Optional<CovidDocument> res = dataAggregatorRepository.findMunicipalityWithMostSingleDoseVaccinationsByProvinceCode(provinceCode);
        if (res.isPresent()) {
            return res.get();
        } else {
            throw new MissingProvinceCodeException("Province code not found");
        }
    }

    @Override
    public CovidDocument findMunicipalityWithMostDoubleDoseVaccinationsByProvinceCode(String provinceCode) throws MissingProvinceCodeException {
        Optional<CovidDocument> res = dataAggregatorRepository.findMunicipalityWithMostDoubleDoseVaccinationsByProvinceCode(provinceCode);
        if (res.isPresent()) {
            return res.get();
        } else {
            throw new MissingProvinceCodeException("Province code not found");
        }
    }
}
