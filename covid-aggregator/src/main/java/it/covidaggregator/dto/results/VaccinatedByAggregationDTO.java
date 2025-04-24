package it.covidaggregator.dto.results;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccinatedByAggregationDTO {
    private String provinciaCodice;
    private int totSingola;
    private int totDoppia;
}
