package pl.agh.edu.host.superstarter;

import java.net.InetAddress;
import java.util.Properties;

import pl.agh.edu.host.superstarter.interfaces.Registrator;

public class RegistratorImpl implements Registrator{

	@Override
	public void registrate(InetAddress address, Integer id) {
		Activator.registrateWorkplace(address, id);	
	}

	@Override
	public Properties get() {
		// TODO Auto-generated method stub
		return Activator.getConfiguration();
	}
	
}
