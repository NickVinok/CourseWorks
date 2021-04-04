package com.diploma.Diploma.DataBase.Model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String login;
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    @Transient
    private String objectType = "user";
}
