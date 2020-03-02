package DataBase.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Equipment {
    @Id
    private long id;
    private double volume;
    private double height;
    private double minTemperature;
    private double maxTemperature;
    private double minPressure;
    private double maxPressure;
}
