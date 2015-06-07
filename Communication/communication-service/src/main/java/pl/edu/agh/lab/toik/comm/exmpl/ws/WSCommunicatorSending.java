package pl.edu.agh.lab.toik.comm.exmpl.ws;

import pl.edu.agh.lab.toik.comm.ICommunicator;
import pl.edu.agh.lab.toik.comm.Message;
import pl.edu.agh.lab.toik.comm.MessageType;
import pl.edu.agh.lab.toik.comm.impl.WSCommunicator;

public class WSCommunicatorSending {

	public static void main(String[] args) throws InterruptedException {

		ICommunicator communicator = new WSCommunicator(
				"http://127.0.0.1:9010/helloWorldSending");

		while (true) {
			Message message = new Message();
			message.setType(MessageType.REPORT);
			message.setValue("testMessage");

			communicator.sendMessage(message,
					"http://127.0.0.1:9000/helloWorldReceiving");
			Thread.sleep(2000);
		}

	}
}
