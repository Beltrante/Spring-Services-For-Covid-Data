package it.covidextractor.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "province_ita")
public class Province {

    @Id
    @Column(name = "codice")
    private String code;

    @Column(unique = true, nullable = false, name = "nome")
    private String name;
}
