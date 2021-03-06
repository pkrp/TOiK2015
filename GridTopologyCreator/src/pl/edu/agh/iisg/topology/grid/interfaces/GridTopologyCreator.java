package pl.edu.agh.iisg.topology.grid.interfaces;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pl.edu.agh.iisg.topology.grid.interfaces.Id;
import pl.edu.agh.iisg.topology.grid.interfaces.Neighbor;

public interface GridTopologyCreator {

	/*
	 * Map<InetAddress, Integer> addresses - map of adresses of each workplace (InetAddress)
	 * with amount of available agents (Integer)
	 */
    public List<Neighbor> getTopology(Map<InetAddress, Integer> addresses);

}
