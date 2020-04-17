package DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EmergencySubType {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("emergencyId")
    @JoinColumn(name = "emergencyId")
    private Emergency emergency;
}
