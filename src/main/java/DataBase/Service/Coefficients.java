package DataBase.Service;

import lombok.Data;
import org.springframework.stereotype.Service;

//TODO потом раскидать коэффициенты, а не хранить в одном месте
@Service
@Data
public class Coefficients {
    //ОТносятся к взрыву паровоздушного облака
    private double substanceParticipationInExplosion=0.1;
    private double speedOfSound = 340;
    private double combustionProductExpansionDegree=7;

    //Относятся к взрыву резервуара с перегретой жидкостью
    private double pressureWaveEnergy=0.5;

    //Относится к огненному шару
    private double meanThermalRadiationIntensity = 350000;

    //Относится к расчёту пожара пролива
    private double straitConcreteCoefficient=150;
    private double straitPrimerCoatingCoefficient=20;
    private double freeFallAcceleration = 9.81;
}
