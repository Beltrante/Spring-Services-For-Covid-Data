package it.covidextractor.services;

import it.covidextractor.dto.CovidKafkaMessage;
import it.covidextractor.dto.CovidData;
import it.covidextractor.exceptions.types.CovidDataSourceException;

import java.time.Instant;
import java.util.List;

public interface DataExtractorService {

    /**
     * Extracts the Covid data and sends it after adding province and timestamp information.
     * <p>
     * This method calls {@link #loadFromUrl()} to fetch Covid data, adds province and time data
     * using {@link #addProvinceAndTimeData(List, Instant)}, and then publishes the resulting data
     * through the {@link PublisherService}.
     *
     * @param now The current timestamp to associate with the data.
     * @throws CovidDataSourceException if there is an issue loading or processing the data.
     */
    void extractAndSendData(Instant now) throws CovidDataSourceException;

    /**
     * Loads Covid data from the configured external URL.
     * <p>
     * The method fetches Covid data from the external URL configured in the application properties.
     * The data is returned as a list of {@link CovidData} objects.
     *
     * @return A list of {@link CovidData} objects representing the Covid data.
     * @throws CovidDataSourceException if there is an issue fetching or parsing the data.
     */
    List<CovidData> loadFromUrl() throws CovidDataSourceException;

    /**
     * Adds province and timestamp data to the Covid data list.
     * <p>
     * This method takes the raw Covid data, adds the corresponding province information
     * (from the repository) and a timestamp, and maps the result to a list of
     * {@link CovidKafkaMessage} objects.
     *
     * @param data A list of {@link CovidData} objects representing the Covid data.
     * @param now  The timestamp to associate with the data.
     * @return A list of {@link CovidKafkaMessage} objects with province and time data.
     */
    List<CovidKafkaMessage> addProvinceAndTimeData(List<CovidData> data, Instant now);
}
