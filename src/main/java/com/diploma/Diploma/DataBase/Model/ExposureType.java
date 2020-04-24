package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "exposureType")
public class ExposureType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
    private String measurementUnit;

    @ManyToOne
    @JoinColumn(name = "emergencyId")
    @MapsId("emergencyId")
    private Emergency emergency;
}
