package pl.edu.agh.iisg.topology.grid;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import pl.edu.agh.iisg.topology.grid.interfaces.GridTopologyCreator;

public class Activator implements BundleActivator {
	
	private ServiceRegistration<GridTopologyCreator> serviceRegistration;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		GridTopologyCreator service = new GridTopologyCreatorImpl();
		Dictionary<String, String> props = new Hashtable<String, String>();
		
		serviceRegistration = context.registerService(GridTopologyCreator.class, service, props);
		System.out.println("Grid topology creator is ready!");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
	}

}
