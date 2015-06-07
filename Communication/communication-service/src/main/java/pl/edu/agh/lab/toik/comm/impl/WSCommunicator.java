package pl.edu.agh.lab.toik.comm.impl;

import java.rmi.RemoteException;
import java.util.regex.Pattern;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import pl.edu.agh.lab.toik.comm.ICommunicator;
import pl.edu.agh.lab.toik.comm.IMessageObserver;
import pl.edu.agh.lab.toik.comm.Message;

public class WSCommunicator implements ICommunicator {

	private final String ID_PATTERN_REGEX = "(http://)(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d+)(/)(\\S+)";
	private final Pattern ID_PATTERN = Pattern.compile(ID_PATTERN_REGEX);

	private JaxWsProxyFactoryBean client;

	private MessagingService localService;

	private JaxWsServerFactoryBean svrFactory;

	public WSCommunicator(String id) {
		svrFactory = new JaxWsServerFactoryBean();
		init(id);
	}

	public void init(String id) {
		if (!ID_PATTERN.matcher(id).matches())
			throw new RuntimeException("Provided wrong identifier");

		localService = new MessagingService();
		svrFactory.setServiceClass(IMessagingService.class);
		svrFactory.setAddress(id);
		svrFactory.setServiceBean(localService);
		svrFactory.create();
	}

	public void sendMessage(Message message, String destination) {
		if (!ID_PATTERN.matcher(destination).matches())
			throw new RuntimeException("Provided wrong destination id");

		client = new JaxWsProxyFactoryBean();
		client.setServiceClass(IMessagingService.class);
		client.setAddress(destination);
		IMessagingService remoteService = (IMessagingService) client.create();
		try {
			remoteService.invokeCommunication(message);
		} catch (RemoteException e) {
			e.printStackTrace(); // won't occur with this implementation
		}
	}

	public void addMessageObserver(IMessageObserver observer) {
		localService.addMessageHandler(observer);
	}

	public void removeMessageObserver(IMessageObserver observer) {
		localService.removeMessageHandler(observer);
	}
}
