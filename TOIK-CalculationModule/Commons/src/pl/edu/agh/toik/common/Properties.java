package pl.edu.agh.toik.common;

import java.util.ArrayList;
import java.util.List;

public class Properties {

	private List<String> simulationSubsteps;
	
	public Properties(){
		simulationSubsteps = new ArrayList<String>();
	}
	
	public void setSimulationSubsteps(List<String> simulationSubsteps){
		this.simulationSubsteps = simulationSubsteps;
	}
	
	public List<String> getSimulationSubsteps(){
		return simulationSubsteps;
	}
	
}
