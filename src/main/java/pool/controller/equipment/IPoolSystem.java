package pool.controller.equipment;

import pool.controller.Location;
import pool.controller.Options;
import pool.controller.Owner;

public interface IPoolSystem {
    String getAlias();
    void setAlias(String alias);
    Owner getOwner();
    Options getOptions();
    Location getLocation();
//    IEquipment getEquipment();
//    ConfigVersion getConfigVersion();
//    Collection<IBody> getBodies();
//    Collection<ISchedule> getSchedules();
//    Collection<ICircuit> getCircuits();
//    Collection<IFeature> getFeatures()n;
//    Collection<IPump> getPumps();
//    Collection<IChlorinator> getChlorinators();
//    Collection<IValve> getValves();
//    Collection<IHeater> getHeaters();
//    Collection<ICover> getCovers();
//    Collection<ICircuitGroup> getCircuitGroups();
//    Collection<IRemote> getRemotes();
//    Security getSecurity();
}
