package DataBase.Model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ExplosionSensitivityClass {
    private long id;
    private int classificationNumber;
}
