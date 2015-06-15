package pl.edu.agh.toik.workplace.basic;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import pl.agh.edu.host.superstarter.interfaces.Registrator;
import pl.edu.agh.lab.toik.comm.ICommunicator;
import pl.edu.agh.lab.toik.comm.Message;
import pl.edu.agh.lab.toik.comm.MessageType;
import pl.edu.agh.toik.workplace.IWorkplace;

public class WorkplaceActivator implements BundleActivator{

	ServiceRegistration<IWorkplace> workplaceService;
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Workplace activator start");

		ServiceReference starterService = context.getServiceReference(Registrator.class.getName());
		Registrator registrator = context.getService(starterService);
		
		ServiceReference communicatorService = context.getServiceReference(ICommunicator.class.getName());
		ICommunicator communicator = context.getService(communicatorService);
		
		Dictionary props = new Hashtable();
		BasicWorkplace workplace = new BasicWorkplace(registrator, communicator);
		workplaceService = context.registerService(IWorkplace.class, workplace, props);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Workplace activator stop");
	}

}
