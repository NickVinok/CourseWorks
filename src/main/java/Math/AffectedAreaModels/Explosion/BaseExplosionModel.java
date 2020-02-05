package Math.AffectedAreaModels.Explosion;

import Math.AffectedAreaModels.BaseModel;

import java.util.ArrayList;

public interface BaseExplosionModel extends BaseModel {
    ArrayList<Double> getExcessPressure();
    ArrayList<Double> getImpulse();
}
