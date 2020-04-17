package DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Coefficients {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
    private String measurementUnit;

    @OneToOne(mappedBy = "coefficients", cascade = CascadeType.ALL)
    private GeneralCoefficients generalCoefficients;
}
