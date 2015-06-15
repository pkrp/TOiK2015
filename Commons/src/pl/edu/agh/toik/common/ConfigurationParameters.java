package pl.edu.agh.toik.common;

public enum ConfigurationParameters {
	
	STEPS_COUNT("STEPS_COUNT"), AGENTS_COUNT("AGENTS_COUNT"), POPULATION_SIZE("POPULATION_SIZE"), SUBSTEPS_LIST("SUBSTEPS_LIST"), WORKPLACE_ADDRESSES("STEP_ACTIONS"),
	TOPOLOGY("TOPOLOGY");
	
	String value;
	ConfigurationParameters(String value){
		this.value = value;
	}
}
