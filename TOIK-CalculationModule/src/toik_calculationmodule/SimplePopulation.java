package toik_calculationmodule;

import java.util.ArrayList;
import java.util.List;

public class SimplePopulation implements Population {

	private static final int initialPopulation = 100;
	private List<Individual> population;
	
	public SimplePopulation(){
		population = new ArrayList<Individual>(initialPopulation);
	}
	
	@Override
	public List<Individual> getPopulation(){
		return population;
	}
	
	@Override
	public void setPopulation(List<Individual> population){
		this.population = population;
	}

	@Override
	public int getSizeOfPopulation() {
		return population.size();
	}
	
}
