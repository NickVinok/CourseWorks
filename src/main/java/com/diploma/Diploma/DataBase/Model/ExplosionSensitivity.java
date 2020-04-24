package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "explosionSensitivity")
public class ExplosionSensitivity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private int classificationNumber;
}
