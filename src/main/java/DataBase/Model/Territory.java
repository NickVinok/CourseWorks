package DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Territory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private double distanceFromPreviousTerritory;
    private double populationDensity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("zoneId")
    @JoinColumn(name = "zoneId")
    private Zone zone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("enterpriseId")
    @JoinColumn(name = "enterpriseId")
    private Enterprise enterprise;
}
