package pl.edu.agh.iisg.topology;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import pl.edu.agh.iisg.topology.interfaces.TopologyCreator;

public class Activator implements BundleActivator {
	
	private ServiceRegistration<TopologyCreator> serviceRegistration;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		TopologyCreator service = new RingTopologyCreator();

		serviceRegistration = context.registerService(TopologyCreator.class, service, null);
		System.out.println("MyService service is ready!");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
	}

}
