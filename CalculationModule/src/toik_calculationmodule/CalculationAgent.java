package toik_calculationmodule;

import pl.edu.agh.toik.common.AgentConfiguration;
import pl.edu.agh.toik.workplace.IWorkplace;
import toik_calculationmodule.population.Population;

public interface CalculationAgent {
	boolean initializeAgent(IWorkplace workplace, AgentConfiguration configuration, Integer id);
	void step();
	void injectPopulation(Population population);
}
