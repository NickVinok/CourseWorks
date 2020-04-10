package DataBase.Model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Coefficients {
    @Id
    private long id;
    private String name;
    private String measurementUnit;

    @OneToOne(mappedBy = "coefficients", cascade = CascadeType.ALL)
    private GeneralCoefficients generalCoefficients;
}
