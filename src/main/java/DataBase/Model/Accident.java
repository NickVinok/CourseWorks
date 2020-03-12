package DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Accident {
    @Id
    private long id;

    private double holeDiameter;
    private double depressurizationFrequency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("equipmentTypeId")
    @JoinColumn(name = "equipmentTypeId")
    private EquipmentType equipmentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("eventId")
    @JoinColumn(name = "eventId")
    private Event event;
}
