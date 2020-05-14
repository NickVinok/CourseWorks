package com.diploma.Diploma.DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class EmergencyScenarioKey implements Serializable {
        @Column(name = "substanceTypeId")
        private long substanceTypeId;
        @Column(name = "destructionTypeId")
        private long destructionTypeId;
        @Column(name = "emergencySubTypeId")
        private long emergencySubTypeId;


        public EmergencyScenarioKey(long substanceTypeId, long destructionTypeId, long emergencySubTypeId){
            this.substanceTypeId = substanceTypeId;
            this.destructionTypeId = destructionTypeId;
            this.emergencySubTypeId = emergencySubTypeId;
        }
}
