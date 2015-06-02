package toik_calculationmodule;

import pl.edu.agh.lab.toik.comm.Communication;
import toik_calculation.topology.TopologyCreator;

public interface CalculationAgent {
	boolean initializeAgent(Configuration configuration, TopologyCreator topology, Communication communication);
	void destructAgent();
	void startSimulation();
	void injectPopulation(Population population);
}
