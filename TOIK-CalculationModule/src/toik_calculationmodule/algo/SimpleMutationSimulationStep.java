package toik_calculationmodule.algo;

import java.util.List;
import java.util.Random;

import toik_calculationmodule.Individual;
import toik_calculationmodule.Population;

public class SimpleMutationSimulationStep implements SimulationStep {

	@Override
	public void invoke(Population population) {
		List<Individual> pop = population.getPopulation();
		for(Individual ind : pop){
			ind.setValue(ind.getValue() + (new Random().nextDouble()/100.0)-(1.0/200.0));
		}
		population.setPopulation(pop);
	}

}
