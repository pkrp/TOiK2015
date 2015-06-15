package toik_calculationalgorithmsmodule;

import java.util.ArrayList;
import java.util.List;

import toik_calculationmodule.population.Population;
import toik_calculationmodule.population.Individual;

public class SimpleCrossoverSimulationSubstep implements SimulationSubstep {

	@Override
	public void invoke(Population population) {
		List<Individual> pop = population.getPopulation();
		List<Individual> newPop = new ArrayList<Individual>();
		for(int i=0 ; i<pop.size()-1 ; ++i){
			for(int j=i+1 ; j<pop.size() ; ++j){
				Individual newInd = new Individual();
				newInd.setValue((pop.get(i).getValue() + pop.get(j).getValue())/2.0);
				newPop.add(newInd);
			}
		}
		population.setPopulation(newPop);
	}

	@Override
	public String toString(){
		return "SimpleCrossoverSimulationSubstep";
	}
}
