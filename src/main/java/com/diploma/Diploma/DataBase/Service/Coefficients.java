package com.diploma.Diploma.DataBase.Service;

import lombok.Data;
import org.springframework.stereotype.Service;

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
    private double meanThermalRadiationIntensity = 350; // КВт/м2

    //Относится к расчёту пожара пролива
    private double straitConcreteCoefficient=150;
    private double straitPrimerCoatingCoefficient=20;
    private double freeFallAcceleration = 9.81;

    //Относится к расчёту факельного горения
    private double torchLengthCoefficient=15; //15 при истечении жидкой фазы; 13.5 при истечени паровой фазы; 12.5 при истечении сжатых газов

    //Относится к расчёту количества парогазовой смеси в оборудовании
    private double universalGasConst=8310; //Универсальная газовая постоянная (Дж/Кг/кМоль)

    //Относится к расчёту интенсивности испарения с поверхности испарения
    private double airSpeedAndTemperatureCoefficient=1;

    //Относится к расчёту расхода жидкости при истечении из отверстия
    private double flowRateCoefficient=0.67; //Взял из методички по гидродинамике

    //Относится ко времени экспозции в случае пожара пролива и факельного горения
    private double humanFireDetectionTime = 5;
    private double humanSpeedProceedingToSafeZone = 5;
}
