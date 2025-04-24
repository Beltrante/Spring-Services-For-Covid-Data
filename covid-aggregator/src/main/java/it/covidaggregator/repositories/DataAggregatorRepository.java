package it.covidaggregator.repositories;

import it.covidaggregator.dto.results.VaccinatedByAggregationDTO;
import it.covidaggregator.entities.CovidDocument;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataAggregatorRepository extends MongoRepository<CovidDocument, String> {

    @Aggregation(pipeline = {
            "{'$group': { '_id': null, 'totale': { '$sum': $totSoloDose1 }}}"
    })
    int findTotalFirstDose();

    @Aggregation(pipeline = {
            "{'$group': { '_id': null, 'totale': { '$sum': $totDose2Unica }}}"
    })
    int findTotalSecondDose();

    @Aggregation(pipeline = {
            "{ '$match': { 'provinciaCodice': ?0 } }",
            "{ '$group': { '_id': null, 'totale': { '$sum': $totSoloDose1 }}}"
    })
    Optional<Integer> findTotalFirstDoseByProvinceCode(String provinceCode);

    @Aggregation(pipeline = {
            "{ '$match': { 'provinciaCodice': ?0 } }", // Filtro sulla provincia
            "{ '$group': { '_id': null, 'totale': { '$sum': $totDose2Unica }}}"
    })
    Optional<Integer> findTotalSecondDoseByProvinceCode(String provinceCode);

    @Aggregation(pipeline = {
            "{'$group': {'_id': '$provinciaCodice', " +
                    "'totSingola': {'$sum': '$totSoloDose1'}," +
                    "'totDoppia': {'$sum': '$totDose2Unica'}}}"
    })
    List<VaccinatedByAggregationDTO> findVaccinatedByProvinceCode(String provinceCode);

    @Aggregation(pipeline = {
            "{ $match: { provinciaCodice: ?0 }}",
            "{'$group': {'_id': '$comuneDom', " +
                    "'totSingola': {'$sum': '$totSoloDose1' }," +
                    "'totDoppia': {'$sum': '$totDose2Unica' }}}"
    })
    List<VaccinatedByAggregationDTO> findVaccinatedForMunicipalityByProvinceCode(String provinceCode);

    @Aggregation(pipeline = {
            "{$sort: {totSoloDose1:-1 } }",
            "{$limit: 1}"
    })
    CovidDocument findMunicipalityWithMostSingleDoseVaccinations();

    @Aggregation(pipeline = {
            "{$sort: {totDose2Unica:-1 } }",
            "{$limit: 1}"
    })
    CovidDocument findMunicipalityWithMostDoubleDoseVaccinations();

    @Aggregation(pipeline = {
            "{$sort: {totSoloDose1:1 } }",
            "{$limit: 1}"
    })
    CovidDocument findMunicipalityWithLeastSingleDoseVaccinations();

    @Aggregation(pipeline = {
            "{$sort: {totDose2Unica:1 } }",
            "{$limit: 1}"
    })
    CovidDocument findMunicipalityWithLeastDoubleDoseVaccinations();

    @Aggregation(pipeline = {
            "{ $match: { provinciaCodice: ?0 }}",
            "{$sort: {totSoloDose1:-1 } }",
            "{$limit: 1}"
    })
    Optional<CovidDocument> findMunicipalityWithMostSingleDoseVaccinationsByProvinceCode(String provinceCode);

    @Aggregation(pipeline = {
            "{ $match: { provinciaCodice: ?0 }}",
            "{$sort: {totDose2Unica:-1 } }",
            "{$limit: 1}"
    })
    Optional<CovidDocument> findMunicipalityWithMostDoubleDoseVaccinationsByProvinceCode(String provinceCode);
}
