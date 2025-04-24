package it.covidextractor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class CovidData {
    @JsonProperty("codistat_comune_dom")
    private String codistatComuneDom;
    @JsonProperty("comune_dom")
    private String comuneDom;
    @JsonProperty("provincia_dom")
    private String provinciaDom;
    @JsonProperty("tot_solo_dose_1")
    private String totSoloDose1;
    @JsonProperty("tot_dose_2_unica")
    private String totDose2Unica;
    @JsonProperty("tot_dose_addizionale_booster")
    private String totDoseAddizionaleBooster;
    @JsonProperty("tot_dose_richimm_rich2_")
    private String totDoseRichimmRich2;
}

