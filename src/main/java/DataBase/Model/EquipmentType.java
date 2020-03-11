package DataBase.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class EquipmentType {
    @Id
    private long id;
    private String name;
}