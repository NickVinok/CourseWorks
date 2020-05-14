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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coefficientsId", referencedColumnName = "id")
    private Coefficients coefficients;
    private double value;
}
