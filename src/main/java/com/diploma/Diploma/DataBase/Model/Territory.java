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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "zoneId", referencedColumnName = "id")
    private Zone zone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "enterpriseId", referencedColumnName = "id")
    private Enterprise enterprise;

    @Transient
    private String objectType = "territory";
}
