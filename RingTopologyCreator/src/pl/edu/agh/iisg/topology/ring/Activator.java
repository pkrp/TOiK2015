package pl.edu.agh.iisg.topology.ring;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import pl.edu.agh.iisg.topology.ring.interfaces.RingTopologyCreator;

public class Activator implements BundleActivator {
	
	private ServiceRegistration<RingTopologyCreator> serviceRegistration;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		RingTopologyCreator service = new RingTopologyCreatorImpl();

		serviceRegistration = context.registerService(RingTopologyCreator.class, service, null);
		System.out.println("Ring topology creator is ready!");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
	}

}
