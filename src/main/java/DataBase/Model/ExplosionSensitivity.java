package DataBase.Model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ExplosionSensitivity {
    private long id;
    private int classificationNumber;
}
