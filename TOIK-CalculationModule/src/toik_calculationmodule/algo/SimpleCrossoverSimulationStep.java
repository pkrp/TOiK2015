package toik_calculationmodule.algo;

import java.util.ArrayList;
import java.util.List;

import toik_calculationmodule.Individual;
import toik_calculationmodule.Population;

public class SimpleCrossoverSimulationStep implements SimulationStep {

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

}
