package DataBase.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ClutterClass {
    @Id
    private long id;
    private int classificationNumber;
}
