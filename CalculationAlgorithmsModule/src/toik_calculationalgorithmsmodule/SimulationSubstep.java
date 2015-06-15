package toik_calculationalgorithmsmodule;

import toik_calculationmodule.population.Population;

public interface SimulationSubstep {
	void invoke(Population population);
}
