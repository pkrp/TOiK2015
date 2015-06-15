package toik_calculationalgorithmsmodule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimulationSubstepsManager {
	
	private Map<String, SimulationSubstep> subSteps;
	
	public SimulationSubstepsManager(){
		subSteps = new HashMap<String, SimulationSubstep>();
		subSteps.put(new SimpleCrossoverSimulationSubstep().toString(), new SimpleCrossoverSimulationSubstep());
		subSteps.put(new SimpleMutationSimulationSubstep().toString(), new SimpleMutationSimulationSubstep());
		subSteps.put(new SimpleSelectionSimulationSubstep().toString(), new SimpleSelectionSimulationSubstep());
	}
	public List<String> getAvailableSubstepsForSimulation(){
		Set<String> s = subSteps.keySet();
		List<String> l = new ArrayList<String>();
		l.addAll(s);
		return l;
	}
	public SimulationSubstep getSimulationSubstep(String nameOfSubstep){
		return subSteps.get(nameOfSubstep);
	}
}
