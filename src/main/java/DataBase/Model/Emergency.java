package DataBase.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Emergency {
    @Id
    private long id;
    private String name;
}
