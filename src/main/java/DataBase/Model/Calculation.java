package DataBase.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
public class Calculation {
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "userId")
    private User user;

    private Timestamp time;
    private double collectiveRisk;
    private double matterConsumption;
}
