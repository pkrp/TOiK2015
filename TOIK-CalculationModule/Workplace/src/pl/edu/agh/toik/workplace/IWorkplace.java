package pl.edu.agh.toik.workplace;

import toik_calculationmodule.population.Population;

public interface IWorkplace {

	//Dla agent�w
	public void sendPopulationToAgent(Population population, String workplaceAddress, Integer agentId);
	
}
