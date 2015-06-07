package pl.agh.edu.host.superstarter.util;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import pl.edu.agh.lab.toik.comm.ICommunicator;
import pl.edu.agh.lab.toik.comm.Message;
import pl.edu.agh.lab.toik.comm.MessageType;

public class MessageSender {
	
	private ICommunicator communicator;
	
	public MessageSender(ICommunicator communicator){
		this.communicator = communicator;
	}
	
	public void send(Set<InetAddress> destination, Properties messages, MessageType type) {
		Message message = createMessage(type, messages);
		for(InetAddress workplace : destination) {
			communicator.sendMessage(message, workplace.toString());
		}
	}
	
	private Message createMessage(MessageType type, Properties value){
		Message message = new Message();
		message.setType(type);
		message.setValue(value);
		
		return message;
	}
}
