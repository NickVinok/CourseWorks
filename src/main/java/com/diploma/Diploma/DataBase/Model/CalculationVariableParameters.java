package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "calculationVariableParameters")
public class CalculationVariableParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double windSpeed;
    private double atmosphericPressure;
    private double outsideTemperature;
    private double liquidTemperature;
    private double equipmentPressure;
    private double holeDiameter;
    private double holeHeight;
    private int numberOfWorkers;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "calculationId", referencedColumnName = "id")
    private Calculation calculation;

    @Transient
    private String objectType = "calculationVariableParameters";
}
