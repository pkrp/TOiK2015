package pl.agh.edu.host.superstarter;

import java.net.InetAddress;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import pl.agh.edu.host.superstarter.interfaces.Registrator;
import pl.agh.edu.host.superstarter.util.ConfigurationCreator;
import pl.agh.edu.host.superstarter.util.MessageSender;
import pl.edu.agh.iisg.topology.grid.interfaces.GridTopologyCreator;
import pl.edu.agh.iisg.topology.grid.interfaces.Neighbor;
import pl.edu.agh.lab.toik.comm.ICommunicator;
import pl.edu.agh.lab.toik.comm.MessageType;

public class Activator implements BundleActivator {
	private static Map<InetAddress, Integer> registratedWorkplaces = new HashMap<InetAddress, Integer>();
	private ServiceRegistration<Registrator> serviceRegistration;
	private ServiceReference topologyReference;
	private ServiceReference communicationReference;
	private ICommunicator communicator;
	private MessageSender messageSender;
	/* only for testing */
	private static Properties conf;
	static BundleContext mContex;
	
	public void start(BundleContext context) throws Exception {
		Dictionary<String, String> props = new Hashtable<String, String>();
		context.registerService(Registrator.class, new RegistratorImpl(), props);
		
		topologyReference = context.getServiceReference(GridTopologyCreator.class.getName());
		
		communicationReference = context.getServiceReference(ICommunicator.class.getName());
		communicator = (ICommunicator)context.getService(communicationReference);
		messageSender = new MessageSender(communicator);
		mContex= context;
		new Thread(new Runnable() {
			public void run() {
			try {
			Thread.sleep(500);


			GridTopologyCreator topologyCreator = (GridTopologyCreator)mContex.getService(topologyReference);
			List<Neighbor> topology = topologyCreator.getTopology(registratedWorkplaces);
			
			ConfigurationCreator configurationCreator = new ConfigurationCreator();
			conf = configurationCreator.create(topology);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			System.out.println("Topologysucks");
			messageSender.send(registratedWorkplaces.keySet(), conf , MessageType.CONFIG);
			communicator.addMessageObserver(new MessageHandler());
			while(true){}
			}
			}).start();
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("SuperStarter is shutting down");
	}
	
	public static void registrateWorkplace(InetAddress address, Integer id) {
		registratedWorkplaces.put(address, id);
	}
	/* only for testing */
	public static Properties getConfiguration() {
		return conf;
	}

}
