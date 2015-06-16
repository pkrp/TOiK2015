package pl.agh.edu.host.superstarter.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import pl.edu.agh.toik.common.*;
import pl.edu.agh.iisg.topology.grid.interfaces.Neighbor;
import toik_calculationalgorithmsmodule.SimulationSubstepsManager;

public class ConfigurationCreator {
	
	Properties configuration = new Properties();
	
	public Properties create(List<Neighbor> topology) {	
		try {
			loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
		configuration.put(ConfigurationParameters.STEPS_COUNT, 10);
		configuration.put(ConfigurationParameters.AGENTS_COUNT, 1);
		configuration.put(ConfigurationParameters.POPULATION_SIZE, 10);
		SimulationSubstepsManager s = new SimulationSubstepsManager();
		configuration.put(ConfigurationParameters.SUBSTEPS_LIST, s.getAvailableSubstepsForSimulation());
		configuration.put(ConfigurationParameters.TOPOLOGY, topology);
		
		
		//System.out.println(configuration.getProperty("amountOfIteration"));
		//System.out.println(configuration.getProperty("sizeOfPopulation"));

		return configuration;
	}
	
	private void loadProperties() throws IOException {
		String propFileName = "config.properties";
 
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
		if (inputStream != null) {
			configuration.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
	}
}
