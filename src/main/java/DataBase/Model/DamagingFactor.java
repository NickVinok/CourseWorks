package DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DamagingFactor {
    @Id
    private long id;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("damagingFactorId")
    @JoinColumn(name = "damagingFactorId")
    private DamagingFactorType damagingFactorType;
}
