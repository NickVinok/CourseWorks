package com.diploma.Diploma.DataBase.Model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "emergency")
public class Emergency {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;

    @Transient
    private String objectType = "emergency";
}
