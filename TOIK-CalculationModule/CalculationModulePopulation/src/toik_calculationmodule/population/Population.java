package toik_calculationmodule.population;

import java.util.List;

public interface Population {
	public List<Individual> getPopulation();
	
	public void setPopulation(List<Individual> population);
	
	public int getSizeOfPopulation();
}
