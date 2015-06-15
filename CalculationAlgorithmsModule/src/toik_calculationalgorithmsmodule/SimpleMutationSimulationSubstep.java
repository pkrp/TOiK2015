package toik_calculationalgorithmsmodule;

import java.util.List;
import java.util.Random;

import toik_calculationmodule.population.Individual;
import toik_calculationmodule.population.Population;

public class SimpleMutationSimulationSubstep implements SimulationSubstep {

	@Override
	public void invoke(Population population) {
		List<Individual> pop = population.getPopulation();
		for(Individual ind : pop){
			ind.setValue(ind.getValue() + (new Random().nextDouble()/100.0)-(1.0/200.0));
		}
		population.setPopulation(pop);
	}

	@Override
	public String toString(){
		return "SimpleMutationSimulationSubstep";
	}
}
