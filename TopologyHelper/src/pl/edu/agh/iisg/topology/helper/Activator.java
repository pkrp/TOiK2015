package pl.edu.agh.iisg.topology.helper;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import pl.edu.agh.iisg.topology.helper.NeighborhoodImpl;
import pl.edu.agh.iisg.topology.helper.interfaces.Neighborhood;

public class Activator implements BundleActivator {

	private ServiceRegistration<Neighborhood> serviceRegistration;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		Neighborhood service = new NeighborhoodImpl();

		serviceRegistration = context.registerService(Neighborhood.class, service, null);
		System.out.println("Topology helper is ready!");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
	}

}
