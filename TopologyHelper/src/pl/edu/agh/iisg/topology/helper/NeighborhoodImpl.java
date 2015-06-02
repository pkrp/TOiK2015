package pl.edu.agh.iisg.topology.helper;

import java.net.InetAddress;
import java.util.List;

import pl.edu.agh.iisg.topology.helper.interfaces.Neighbor;

public class NeighborhoodImpl implements pl.edu.agh.iisg.topology.helper.interfaces.Neighborhood {
	
	List<Neighbor> neighborhood;

	public void setNeighborhood(List<Neighbor> neighborhood) {
		this.neighborhood = neighborhood;
	}

	public List<Neighbor> getNeighborhood() {
		return neighborhood;
	}

	public boolean isNeighbor(String id) {
		for (Neighbor neighbor: neighborhood) {
			if (neighbor.getId().toString() == id) {
				return true;
			}
		}
		return false;
	}

	public boolean isNeighbor(InetAddress address, Integer id) {
		return isNeighbor(address.toString() + ":" + id);
	}

}
