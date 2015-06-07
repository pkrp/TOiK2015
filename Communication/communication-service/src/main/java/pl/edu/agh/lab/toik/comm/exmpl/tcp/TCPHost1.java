package pl.edu.agh.lab.toik.comm.exmpl.tcp;

import pl.edu.agh.lab.toik.comm.ICommunicator;
import pl.edu.agh.lab.toik.comm.Message;
import pl.edu.agh.lab.toik.comm.MessageType;
import pl.edu.agh.lab.toik.comm.exmpl.TestMessageHandler;
import pl.edu.agh.lab.toik.comm.impl.TCPCommunicator;

public class TCPHost1 {

	/**
	 * 
	 * Notes for RMI: necessary to have correct CLASSPATH (containing app .jar)
	 * and start rmiregistry before app
	 * 
	 */

	public static void main(String[] args) throws InterruptedException {
		ICommunicator communicator = new TCPCommunicator();
		communicator.init("http://192.168.2.8:8081/host1");
		communicator.addMessageObserver(new TestMessageHandler());

		Message message = new Message();
		message.setType(MessageType.REPORT);
		message.setValue("testMessageFromHost1");

		while (true) {
			communicator.sendMessage(message, "http://192.168.2.4:8081/host2");
			Thread.sleep(5000);
		}
	}
}
