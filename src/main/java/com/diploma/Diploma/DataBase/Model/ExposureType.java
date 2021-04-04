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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emergencyId", referencedColumnName = "id")
    private Emergency emergency;

    @Transient
    private String objectType = "exposureType";
}
