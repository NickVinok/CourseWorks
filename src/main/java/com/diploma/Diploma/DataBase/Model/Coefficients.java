package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "coefficients")
public class Coefficients {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
    private String measurementUnit;

    @Transient
    private String objectType = "coefficients";
}
