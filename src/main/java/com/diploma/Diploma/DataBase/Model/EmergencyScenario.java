package com.diploma.Diploma.DataBase.Model;

import com.diploma.Diploma.DataBase.Model.Keys.EmergencyScenarioKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "emergencyScenario")
public class EmergencyScenario {
    @EmbeddedId
    private EmergencyScenarioKey emergencyScenarioKey;
    //TODO РАЗДЕЛИТЬ ЭТОТ КЛАСС, ТАК КАК КОНЕЧНЫЕ ТОЧКИ ДЕРЕВА И ТЕ, ЧТО В СЕРЕДИНЕ ОЗНАЧАЮТ, ЧТО EMERGENCYSUBTYPE МОЖЕТ БЫТЬ НУЛЕВЫМ
    private double probability;
    private boolean isEnd;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("destructionTypeId")
    @JoinColumn(name = "destructionTypeId")
    private DestructionType destructionTypeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("emergencySubTypeId")
    @JoinColumn(name = "emergencySubTypeId")
    private EmergencySubType emergencySubType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("substanceTypeId")
    @JoinColumn(name = "substanceTypeId")
    private SubstanceType substanceType;
}
