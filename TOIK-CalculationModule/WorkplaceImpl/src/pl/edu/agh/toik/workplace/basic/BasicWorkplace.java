package pl.edu.agh.toik.workplace.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import pl.edu.agh.lab.toik.comm.ICommunicator;
import pl.edu.agh.lab.toik.comm.IMessageObserver;
import pl.edu.agh.lab.toik.comm.Message;
import pl.edu.agh.lab.toik.comm.MessageType;
import pl.edu.agh.toik.common.ConfigurationParameters;
import pl.edu.agh.toik.common.Population;
import pl.edu.agh.toik.common.PopulationMessageValue;
import pl.edu.agh.toik.starter.Registrator;
import pl.edu.agh.toik.topology.TopologyCreator;
import pl.edu.agh.toik.workplace.IWorkplace;
import toik_calculationmodule.CalculationAgent;
import toik_calculationmodule.CalculationAgentImpl;

public class BasicWorkplace implements IWorkplace, IMessageObserver{
	
	private ICommunicator communicator;
	private Registrator registrator;
	private TopologyCreator topologyCreator;
	
	private Properties configuration;
	private Map<Integer, CalculationAgent> agents;
	private Map<Integer, Population> populationMessages;
	
	private int stepsCount;
	private int currentStep = 0;
	
	
	public BasicWorkplace(Registrator registrator, ICommunicator communicator){
		this.communicator = communicator;
		registrator.registrate(getWorkplaceAddress());
		communicator.init(getWorkplaceServiceEndpoint());
		communicator.addMessageObserver(this);
	}

	@Override
	public void handleIncomingMessage(Message message) {
		if(message.getType().equals(MessageType.CONFIG)){
			handleConfigurationMessage(message.getValue());
		}
		
		if(message.getType().equals(MessageType.WORPLACE_INIT)){
			handleInitMessage();
		}
		
		if(message.getType().equals(MessageType.POPULATION)){
			handlePopulationMessage((PopulationMessageValue) message.getValue());
		}
	}
	
	@Override
	public void sendPopulationToAgent(Population population, String workplaceAddress, Integer agentId) {
		if(workplaceAddress.equals(getWorkplaceAddress())){
			populationMessages.put(agentId, population);
		} else {
			Message message = new Message();
			message.setType(MessageType.POPULATION);
			PopulationMessageValue populationMessageValue = new PopulationMessageValue(agentId, population);
			message.setValue(populationMessageValue);
			communicator.sendMessage(message, workplaceAddress);
		}
	}
	
	private void handleConfigurationMessage(Object value){
		this.populationMessages = new HashMap<>();
		this.configuration = (Properties) value;
		this.agents = new HashMap<Integer, CalculationAgent>();
		this.topologyCreator = new TopologyCreator(configuration);
		this.stepsCount = (int) configuration.get(ConfigurationParameters.STEPS_COUNT);
		
		int agentsCount = (int) configuration.get(ConfigurationParameters.AGENTS_COUNT);
		for(int i= 0; i<agentsCount; i++){
			CalculationAgent agent = new CalculationAgentImpl();
			agent.initializeAgent(this, configuration, topologyCreator, i);
			agents.put(i, agent);
		}
	}
	
	private void handleInitMessage(){
		while(currentStep < stepsCount) {
			for(int i = 0; i<agents.keySet().size(); i++){
				CalculationAgent agent = agents.get(i);
				
				Population pendingPopulation = getPendingPopulationMessage(i);
				if(pendingPopulation != null) {
					agent.injectPopulation(pendingPopulation);
				}
				
				agent.step();
			}
			++currentStep;
		}
	}
	
	private void handlePopulationMessage(PopulationMessageValue population) {
		populationMessages.put(population.getAgentId(), population.getPopulation());
	}

	private Population getPendingPopulationMessage(Integer agentNo) {
		return populationMessages.remove(agentNo);
	};
	
	private InetAddress getWorkplaceAddress(){
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getWorkplaceServiceEndpoint(){
		//TODO prawdziwa wartosc
		return "http://localhost:8080/workplace";
	}

}
