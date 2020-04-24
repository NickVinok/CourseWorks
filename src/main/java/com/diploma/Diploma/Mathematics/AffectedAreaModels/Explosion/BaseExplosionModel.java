package com.diploma.Diploma.Mathematics.AffectedAreaModels.Explosion;

import com.diploma.Diploma.Mathematics.AffectedAreaModels.BaseModel;

import java.util.ArrayList;

public interface BaseExplosionModel extends BaseModel {
    ArrayList<Double> getExcessPressure();
    ArrayList<Double> getImpulse();
}
