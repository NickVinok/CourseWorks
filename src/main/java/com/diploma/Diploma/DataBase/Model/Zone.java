package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
    private double probabilityOfPresenceInZone;

    @Transient
    private String objectType = "zone";
}
