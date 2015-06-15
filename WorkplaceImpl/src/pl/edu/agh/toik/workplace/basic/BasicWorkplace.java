package pl.edu.agh.toik.workplace.basic;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import pl.agh.edu.host.superstarter.interfaces.Registrator;
import pl.edu.agh.iisg.topology.helper.interfaces.Neighbor;
import pl.edu.agh.lab.toik.comm.ICommunicator;
import pl.edu.agh.lab.toik.comm.IMessageObserver;
import pl.edu.agh.lab.toik.comm.Message;
import pl.edu.agh.lab.toik.comm.MessageType;
import pl.edu.agh.toik.common.AgentConfiguration;
import pl.edu.agh.toik.common.ConfigurationParameters;
import pl.edu.agh.toik.common.PopulationMessageValue;
import pl.edu.agh.toik.workplace.IWorkplace;
import toik_calculationmodule.CalculationAgent;
import toik_calculationmodule.CalculationAgentImpl;
import toik_calculationmodule.population.Population;

public class BasicWorkplace implements IWorkplace, IMessageObserver{
	
	private ICommunicator communicator;
	private InetAddress workplaceAddress;
	
	private Properties configuration;
	private Map<Integer, CalculationAgent> agents;
	private Map<Integer, Population> populationMessages;
	
	private int stepsCount;
	private int currentStep;
	
	public BasicWorkplace(Registrator registrator, ICommunicator communicator){
		this.communicator = communicator;	
		try {
			this.workplaceAddress = getWorkplaceAddress();
		} catch (SocketException e) {
			System.out.println("Error getting workplace address");
		}
		
		//Sk¹d w momencie rejestracji mamy znaæ liczbê agentów ???
		registrator.registrate(workplaceAddress, 1);
		communicator.init(getWorkplaceServiceEndpoint());
		communicator.addMessageObserver(this);
	}

	@Override
	public void handleIncomingMessage(Message message) {
		if(message.getType().equals(MessageType.CONFIG)){
			handleConfigurationMessage(message.getValue());
		}
		
		if(message.getType().equals(MessageType.WORKPLACE_INIT)){
			handleInitMessage();
		}
		
		if(message.getType().equals(MessageType.POPULATION)){
			handlePopulationMessage((PopulationMessageValue) message.getValue());
		}
	}
	
	@Override
	public void sendPopulationToAgent(Population population, String workplaceAddress, Integer agentId) {
		Message message = new Message();
		message.setType(MessageType.POPULATION);
		PopulationMessageValue populationMessageValue = new PopulationMessageValue(agentId, population);
		message.setValue(populationMessageValue);
		communicator.sendMessage(message, workplaceAddress);
	}
	
	private void handleConfigurationMessage(Object value){
		this.populationMessages = new HashMap<>();
		this.configuration = (Properties) value;
		this.agents = new HashMap<Integer, CalculationAgent>();
		this.stepsCount = (int) configuration.get(ConfigurationParameters.STEPS_COUNT);
		
		int agentsCount = (int) configuration.get(ConfigurationParameters.AGENTS_COUNT);
		for(int i= 0; i<agentsCount; i++){
			CalculationAgent agent = new CalculationAgentImpl();
			agent.initializeAgent(this, extractAgentsConfigurationObject(configuration), i);
			agents.put(i, agent);
		}
	}
	
	private void handleInitMessage(){
		currentStep = 0;
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
	
	private InetAddress getWorkplaceAddress() throws SocketException{
		for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
            NetworkInterface intf = en.nextElement();
            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                InetAddress inetAddress = enumIpAddr.nextElement();
                if (!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address) {
                    return inetAddress;
                }
            }
        }
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private AgentConfiguration extractAgentsConfigurationObject(Properties configurationProperties){
		AgentConfiguration configuration = new AgentConfiguration();
		configuration.setNeighbors((List<Neighbor>) configurationProperties.get(ConfigurationParameters.TOPOLOGY));
		configuration.setSimulationSubsteps((List<String>) configurationProperties.get(ConfigurationParameters.SUBSTEPS_LIST));
		return configuration;
	}
	
	private String getWorkplaceServiceEndpoint(){
		return workplaceAddress.getHostAddress().toString();
	}

}
