package it.covidaggregator.dto.mappers;

import it.covidaggregator.dto.CovidKafkaMessage;
import it.covidaggregator.entities.CovidDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.Date;

@Mapper(componentModel = "Spring")
public interface CovidDataMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "totSoloDose1", target = "totSoloDose1", qualifiedByName = "stringToInt")
    @Mapping(source = "totDose2Unica", target = "totDose2Unica", qualifiedByName = "stringToInt")
    @Mapping(source = "totDoseAddizionaleBooster", target = "totDoseAddizionaleBooster", qualifiedByName = "stringToInt")
    @Mapping(source = "totDoseRichimmRich2", target = "totDoseRichimmRich2", qualifiedByName = "stringToInt")
    @Mapping(source = "downloadTime", target = "downloadTime", qualifiedByName = "stringToDate")
    CovidDocument dtoToEntity(CovidKafkaMessage dto);

    @Named("stringToInt")
    default int stringToInt(String string) {
        if (string == null || string.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Named("stringToDate")
    default Date stringToDate(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        try {
            Instant instant = Instant.parse(string);
            return Date.from(instant);
        } catch (DateTimeException e) {
            return null;
        }
    }

}