package pl.edu.agh.toik.common;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.iisg.topology.helper.interfaces.Neighbor;

public class AgentConfiguration {

	private List<String> simulationSubsteps;
	
	private List<Neighbor> neigbors;
	
	public AgentConfiguration(){
		simulationSubsteps = new ArrayList<String>();
	}
	
	public void setSimulationSubsteps(List<String> simulationSubsteps){
		this.simulationSubsteps = simulationSubsteps;
	}
	
	public List<String> getSimulationSubsteps(){
		return simulationSubsteps;
	}
	
	public void setNeighbors(List<Neighbor> neighbors) {
		this.neigbors = neighbors;
	}
	
	public List<Neighbor> getNeighbors(){
		return neigbors;
	}
	
}
