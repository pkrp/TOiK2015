package toik_calculationmodule;

import pl.edu.agh.toik.common.Properties;
import pl.edu.agh.toik.topology.TopologyCreator;
import pl.edu.agh.toik.workplace.IWorkplace;
import toik_calculationmodule.population.Population;

public interface CalculationAgent {
	boolean initializeAgent(IWorkplace workplace, Properties configuration, TopologyCreator topology, Integer id);
	void step();
	void injectPopulation(Population population);
}
