package it.covidextractor.dto.mappers;

import it.covidextractor.dto.CovidKafkaMessage;
import it.covidextractor.dto.CovidData;
import it.covidextractor.entities.Province;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CovidKafkaMessageMapperImpl implements CovidKafkaMessageMapper {

    @Override
    public CovidKafkaMessage covidToKafkaMessage(CovidData response, String downloadTime, String provinceCode) {
        if (response == null) {
            return null;
        }
        return new CovidKafkaMessage(
                response.getCodistatComuneDom(),
                response.getComuneDom(),
                response.getProvinciaDom(),
                response.getTotSoloDose1(),
                response.getTotDose2Unica(),
                response.getTotDoseAddizionaleBooster(),
                response.getTotDoseRichimmRich2(),
                provinceCode,
                downloadTime
        );
    }

    @Override
    public List<CovidKafkaMessage> covidListToKafkaMessageList(List<CovidData> responseList, List<Province> provinceList, String downloadTime) {
        if (responseList == null) {
            return null;
        }
        if (provinceList == null) {
            return null;
        }
        return responseList.stream().flatMap(response -> provinceList.stream()
                .filter(province -> province.getName().equalsIgnoreCase(response.getProvinciaDom()))
                .map(province -> covidToKafkaMessage(response, downloadTime, province.getCode()))).collect(Collectors.toList());
    }
}