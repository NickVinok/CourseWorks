package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "calculation")
public class Calculation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    private double collectiveRisk;
    private double matterConsumption;
    private double matterQuantity;
}
