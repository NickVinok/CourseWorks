package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name = "generalCoefficients")
public class GeneralCoefficients {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @MapsId("coefficientsId")
    @JoinColumn(name = "coefficientsId")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Coefficients coefficients;
    private double value;
}
