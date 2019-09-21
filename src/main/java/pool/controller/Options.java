package pool.controller;

import java.util.Collection;

public class Options {
    private int clockMode;
    private String units;
    private String clockSource;
    private boolean adjustDST;
    private boolean manualPriority;
    private boolean vacationMode;
    private boolean manualHeat;
    private boolean pumpDelay;
    private boolean cooldownDelay;
    private Collection<Sensor> sensors;
    private int airTempAdj;
    private int waterTempAdj1;
    private int solarTempAdj1;
    private int waterTempAdj2;
    private int solarTempAdj2;
    
	public int getClockMode() {
		return clockMode;
	}
	public void setClockMode(int clockMode) {
		this.clockMode = clockMode;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getClockSource() {
		return clockSource;
	}
	public void setClockSource(String clockSource) {
		this.clockSource = clockSource;
	}
	public boolean isAdjustDST() {
		return adjustDST;
	}
	public void setAdjustDST(boolean adjustDST) {
		this.adjustDST = adjustDST;
	}
	public boolean isManualPriority() {
		return manualPriority;
	}
	public void setManualPriority(boolean manualPriority) {
		this.manualPriority = manualPriority;
	}
	public boolean isVacationMode() {
		return vacationMode;
	}
	public void setVacationMode(boolean vacationMode) {
		this.vacationMode = vacationMode;
	}
	public boolean isManualHeat() {
		return manualHeat;
	}
	public void setManualHeat(boolean manualHeat) {
		this.manualHeat = manualHeat;
	}
	public boolean isPumpDelay() {
		return pumpDelay;
	}
	public void setPumpDelay(boolean pumpDelay) {
		this.pumpDelay = pumpDelay;
	}
	public boolean isCooldownDelay() {
		return cooldownDelay;
	}
	public void setCooldownDelay(boolean cooldownDelay) {
		this.cooldownDelay = cooldownDelay;
	}
	public Collection<Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(Collection<Sensor> sensors) {
		this.sensors = sensors;
	}
	public int getAirTempAdj() {
		return airTempAdj;
	}
	public void setAirTempAdj(int airTempAdj) {
		this.airTempAdj = airTempAdj;
	}
	public int getWaterTempAdj1() {
		return waterTempAdj1;
	}
	public void setWaterTempAdj1(int waterTempAdj1) {
		this.waterTempAdj1 = waterTempAdj1;
	}
	public int getSolarTempAdj1() {
		return solarTempAdj1;
	}
	public void setSolarTempAdj1(int solarTempAdj1) {
		this.solarTempAdj1 = solarTempAdj1;
	}
	public int getWaterTempAdj2() {
		return waterTempAdj2;
	}
	public void setWaterTempAdj2(int waterTempAdj2) {
		this.waterTempAdj2 = waterTempAdj2;
	}
	public int getSolarTempAdj2() {
		return solarTempAdj2;
	}
	public void setSolarTempAdj2(int solarTempAdj2) {
		this.solarTempAdj2 = solarTempAdj2;
	}
}
