package toik_calculationmodule;

import java.util.ArrayList;
import java.util.List;

import toik_calculationmodule.algo.*;

public class Configuration {

	private List<SimulationStep> simulationSteps;
	
	public Configuration(){
		simulationSteps = new ArrayList<SimulationStep>();
	}
	
	public void setSimulationSteps(List<SimulationStep> simulationSteps){
		this.simulationSteps = simulationSteps;
	}
	
	public List<SimulationStep> getSimulationSteps(){
		return simulationSteps;
	}
	
}
