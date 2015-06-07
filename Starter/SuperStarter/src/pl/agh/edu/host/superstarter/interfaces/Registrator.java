package pl.agh.edu.host.superstarter.interfaces;

import java.net.InetAddress;
import java.util.Properties;

public interface Registrator {

	public void registrate(InetAddress address, Integer id);
	/* only for testing */
	public Properties get(); 
	
}
