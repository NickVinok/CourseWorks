package com.diploma.Diploma.DataBase.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private double area;
    private String floorType;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "clutterClassId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClutterClass clutterClass;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "enterpriseId", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Enterprise enterprise;

    @Transient
    private String objectType = "department";
}
