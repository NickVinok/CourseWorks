package DataBase.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class DamagingFactorType {
    @Id
    private long id;
    private String name;
}
