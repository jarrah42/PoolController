package pool.controller.equipment;

public interface IPump {
    public enum PumpType {
    	VS, VSF, VF, NONE
    };
    public enum VirtualControllerType {
    	ALWAYS, NEVER, DEFAULT
    };
    public enum VirtualControllerStatus {
    	ENABLED,
    	DISABLED
    };
    public enum PumpSpeedType { RPM, GPM };
    
    public interface CurrentRunning
    {
        String getMode();
        int getValue();
        int getRemainingDuration();
    }
    
    public interface ExternalProgram {
    	int getProg1();
    	int getProg2();
    	int getProg3();
    	int getProg4();
    }
    
    int getNumber();
    String getName();
    String getFriendlyName();
    PumpType getPumpType();
    String getTime();
    int getRun();
    int getMode();
    int getDriveState();
    int getWatts();
    int getRPM();
    int getGPM();
    int getPPC();
    int getErr();
    int getTimer();
    int getDuration();
    CurrentRunning getCurrentRunning();
    int getCurrentProgram();
    ExternalProgram getExternalProgram();
    int getRemoteControl();
    int getPower();
    VirtualControllerType getVirtualControllerType();
    VirtualControllerStatus getVirtualControllerStatus();
    
    public interface ExtendedConfig {
        ConfigPrimingValues getPrimingValues();
        ConfigCircuitSlotValues[] getCircuitSlotValues();
        ConfigFilterValues getFilterValues();
        ConfigVacuumValues getVacuumValues();
        ConfigVFPrimingValues getVFPrimingValues();
        ConfigBackwashValues getBackwashValues();
        PumpType getBackgroundCircuit();

        void setSpeed(int circuitSlot, int speed);
        void setCircuit(int circuitSlot, int circuit);
        void setType(PumpType type);
        void setRPMGPM(int circuitSlot, PumpSpeedType speedType);
    }

    public interface ConfigFilterValues
    {
        int getPoolSize();
        int getTurnOvers();
        int getManualFilterGPM();
    }

    public interface ConfigVacuumValues
    {
        int getFlow();
        int getTime();
    }

    // Values specific to the VF Pump Config
    public interface ConfigVFPrimingValues
    {
        int getMaxFlow();
        int getMaxTime();
        int getSystemMaxTime();
    }

    public interface ConfigBackwashValues
    {
        int getMaxPressureIncrease();
        int getFlow();
        int getTime();
        int getRinseTime();
    }

    public interface ConfigCircuitSlotValues
    {
        int getNumber();
        String getFriendlyName();
        PumpSpeedType getFlag();
        int getRpm();
        int getGpm();
    }

    public interface ConfigPrimingValues
    {
        int getPrimingMinutes();
        int getRpm();
    }
}