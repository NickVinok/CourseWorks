package DataBase.Model;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class GeneralCoefficients {
    @OneToOne
    @MapsId("coefficientsId")
    @JoinColumn(name = "coefficientsId")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Coefficients coefficients;
    private double value;
}
