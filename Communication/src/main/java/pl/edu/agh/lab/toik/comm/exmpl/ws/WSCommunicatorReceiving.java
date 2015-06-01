package pl.edu.agh.lab.toik.comm.exmpl.ws;

import pl.edu.agh.lab.toik.comm.ICommunicator;
import pl.edu.agh.lab.toik.comm.exmpl.TestMessageHandler;
import pl.edu.agh.lab.toik.comm.impl.WSCommunicator;

public class WSCommunicatorReceiving {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Starting");

		ICommunicator communicator = new WSCommunicator(
				"http://127.0.0.1:9000/helloWorldReceiving");
		communicator.addMessageObserver(new TestMessageHandler());

		Thread.sleep(5 * 60 * 1000);
		System.out.println("Exiting");
		System.exit(0);
	}

}
