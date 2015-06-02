package toik_calculationmodule;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.lab.toik.comm.Communication;
import pl.edu.agh.lab.toik.comm.IMessageObserver;
import pl.edu.agh.lab.toik.comm.Message;
import pl.edu.agh.lab.toik.comm.MessageType;
import toik_calculation.topology.TopologyCreator;
import toik_calculationmodule.algo.SimulationStep;

public class CalculationAgentImpl implements CalculationAgent {

	private Configuration configuration;
	private TopologyCreator topologyCreator;
	private Communication communication;
	private SimplePopulation simplePopulation;
	private List<Population> populationToInject;
	
	public CalculationAgentImpl(){
		simplePopulation = new SimplePopulation();
		populationToInject = new ArrayList<Population>();
	}
	
	@Override
	public boolean initializeAgent(Configuration configuration,
			TopologyCreator topology, Communication communication) {
		this.configuration = configuration;
		this.topologyCreator = topology;
		this.communication = communication;
		return true;
	}

	@Override
	public void destructAgent() {
	
	}

	@Override
	public void startSimulation() {
		//dodanie populacji wstrzyknietej
		List<Individual> pop = simplePopulation.getPopulation();
		for(Population p : populationToInject){
			pop.addAll(p.getPopulation());
		}
		populationToInject.clear();
		simplePopulation.setPopulation(pop);
		//wykonanie symulacji
		for(SimulationStep st : configuration.getSimulationSteps()){
			st.invoke(simplePopulation);
		}
		//przeslanie danych
		//TODO
	}

	@Override
	public void injectPopulation(Population population) {
		populationToInject.add(population);
	}

	class TestMessageHandler implements IMessageObserver {

		@Override
		public void handleIncomingMessage(Message message) {
			if(message.getType() == MessageType.POPULATION){
				injectPopulation((Population)message.getValue());
			}
		}
	}
	
}
