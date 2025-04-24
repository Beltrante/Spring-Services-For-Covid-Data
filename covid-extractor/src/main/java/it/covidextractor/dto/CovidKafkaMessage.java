package it.covidextractor.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CovidKafkaMessage {

    private String codistatComuneDom;

    private String comuneDom;

    private String provinciaDom;

    private String totSoloDose1;

    private String totDose2Unica;

    private String totDoseAddizionaleBooster;

    private String totDoseRichimmRich2;

    private String provinciaCodice;

    private String downloadTime;
}
