package DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    private long id;

    private String name;
    private String surname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("roleId")
    @JoinColumn(name = "roleId")
    private Role role;
}
