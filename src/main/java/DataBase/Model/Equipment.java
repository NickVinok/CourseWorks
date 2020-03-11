package DataBase.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Equipment {
    @Id
    private long id;
    private String name; //наименованование оборудования
    private String equipmentMark; //марка оборудования
    private double volume;
    private double height;
    private double minTemperature;
    private double maxTemperature;
    private double minPressure;  //неуверен есть ли смысл, ибо минимальное давение по сути - атмосферное
    private double maxPressure;
}
