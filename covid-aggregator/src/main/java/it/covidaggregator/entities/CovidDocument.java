package it.covidaggregator.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
@CompoundIndexes({
        @CompoundIndex(name = "idx_provincia",def = "{'provinciaCodice': 1}"),
        @CompoundIndex(name = "provincia_comune_idx",def = "{'provinciaCodice': 1, 'comuneDom': 1}"),
        @CompoundIndex(name = "idx_totSoloDose1",def = "{'totSoloDose1': -1}"),
        @CompoundIndex(name = "idx_totDose2Unica",def = "{'totDose2Unica': -1}"),
})
public class CovidDocument {
    @Id
    private String id;

    @Field
    private String codistatComuneDom;
    private String comuneDom;
    private String provinciaDom;
    private String provinciaCodice;
    private int totSoloDose1;
    private int totDose2Unica;
    private int totDoseAddizionaleBooster;
    private int totDoseRichimmRich2;
    private Date downloadTime;
}
