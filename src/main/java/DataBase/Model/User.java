package DataBase.Model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;
}
