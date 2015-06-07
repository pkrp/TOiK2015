package pl.agh.edu.host.superstarter;

import pl.edu.agh.lab.toik.comm.IMessageObserver;
import pl.edu.agh.lab.toik.comm.Message;

public class MessageHandler implements IMessageObserver{

	int bestResult = 0;
	
	@Override
	public void handleIncomingMessage(Message message) {
		System.out.println("Result: " + message.getValue());
		logBestResult((Integer)(message.getValue()));
	}
	
	private void logBestResult(int result) {
		if(result > bestResult) {
			bestResult = result;
			System.out.println("Best result: " + bestResult);
		}
	}
}
