package Mathematics.AffectedAreaModels.Explosion;

import Mathematics.AffectedAreaModels.BaseModel;

import java.util.ArrayList;

public interface BaseExplosionModel extends BaseModel {
    ArrayList<Double> getExcessPressure();
    ArrayList<Double> getImpulse();
}
