package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "enterprise")
public class Enterprise {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private double area;
}
