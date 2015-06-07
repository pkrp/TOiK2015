package pl.edu.agh.lab.toik.comm.exmpl;

import pl.edu.agh.lab.toik.comm.IMessageObserver;
import pl.edu.agh.lab.toik.comm.Message;

public class TestMessageHandler implements IMessageObserver {

	public void handleIncomingMessage(Message message) {
		System.out.println(message.getValue());
	}
}
