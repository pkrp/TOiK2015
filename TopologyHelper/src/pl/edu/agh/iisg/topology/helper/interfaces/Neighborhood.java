package pl.edu.agh.iisg.topology.helper.interfaces;

import java.net.InetAddress;
import java.util.List;

public interface Neighborhood {
	public void setNeighborhood(List<Neighbor> neighborhood);

	public List<Neighbor> getNeighborhood();

	public boolean isNeighbor(String id);

	public boolean isNeighbor(InetAddress address, Integer id);
}
