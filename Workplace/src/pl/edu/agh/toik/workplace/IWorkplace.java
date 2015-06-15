package pl.edu.agh.toik.workplace;

import toik_calculationmodule.population.Population;

public interface IWorkplace {

	public void sendPopulationToAgent(Population population, String targetWorkplaceAddress, Integer targetAgentId);
	
}
