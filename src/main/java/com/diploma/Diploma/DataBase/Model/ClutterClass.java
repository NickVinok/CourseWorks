package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clutterClass")
public class ClutterClass {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private int classificationNumber;
}
