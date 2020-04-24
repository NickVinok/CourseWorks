package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "territory")
public class Territory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private double distanceFromPreviousTerritory;
    private double populationDensity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("zoneId")
    @JoinColumn(name = "zoneId")
    private Zone zoneId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("enterpriseId")
    @JoinColumn(name = "enterpriseId")
    private Enterprise enterpriseId;
}
