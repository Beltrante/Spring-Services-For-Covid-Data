package it.covidextractor.dto.mappers;

import it.covidextractor.dto.CovidData;
import it.covidextractor.dto.CovidKafkaMessage;
import it.covidextractor.entities.Province;

import java.util.List;

public interface CovidKafkaMessageMapper {

    /**
     * Converts a single {@link CovidData} object into a {@link CovidKafkaMessage} object.
     * <p>
     * This method takes a single Covid data record and maps it to a {@link CovidKafkaMessage}
     * by associating the data with the provided download time and province code.
     *
     * @param response     The {@link CovidData} object containing the Covid data to be converted.
     * @param downloadTime The timestamp of the data download.
     * @param provinceCode The code of the province associated with the Covid data.
     * @return A {@link CovidKafkaMessage} containing the mapped Covid data.
     */
    CovidKafkaMessage covidToKafkaMessage(CovidData response, String downloadTime, String provinceCode);

    /**
     * Converts a list of {@link CovidData} objects to a list of {@link CovidKafkaMessage} objects.
     * <p>
     * This method maps each {@link CovidData} object in the provided list to a corresponding
     * {@link CovidKafkaMessage}. It also uses a list of {@link Province} to associate the correct
     * province codes to the data and assigns the provided download time.
     *
     * @param responseList A list of {@link CovidData} objects to be converted.
     * @param provinceList A list of {@link Province} objects used to associate the Covid data with the correct province.
     * @param downloadTime The timestamp of the data download.
     * @return A list of {@link CovidKafkaMessage} objects corresponding to the provided Covid data.
     */
    List<CovidKafkaMessage> covidListToKafkaMessageList(List<CovidData> responseList, List<Province> provinceList, String downloadTime);
}
