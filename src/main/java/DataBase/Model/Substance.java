package DataBase.Model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
public class Substance {
    @Id
    private long id;
    private double specificHeat; //удельная теплоёмкость
    private double beta; //корректировачный параметр для расчёта режима сгорания облака
    private double boilingTemperature; //Температура кипения при нормальном атмосферном давлении
    private String type; //Тип вещества (нефтепродукт, или однокомпонентная жидкость и т.д.)
    private double specificBurnoutRate; //Удельная теплота сгорания кДж/кг
    private double specificEvaporationRate; //Удельная теплота испарения жидкости кДж/кг

    //TODO Нормально вставить декоратор от Hibernate
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "explosionSensitivityId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ExplosionSensitivityClass explosionSensitivityClass;
}
