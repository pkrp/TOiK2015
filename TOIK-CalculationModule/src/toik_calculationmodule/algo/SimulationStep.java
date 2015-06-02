package toik_calculationmodule.algo;

import toik_calculationmodule.Population;

public interface SimulationStep {
	void invoke(Population population);
}
