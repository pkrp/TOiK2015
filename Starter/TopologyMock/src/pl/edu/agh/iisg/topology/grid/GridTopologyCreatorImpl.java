package pl.edu.agh.iisg.topology.grid;

import pl.edu.agh.iisg.topology.grid.interfaces.Neighbor;
import pl.edu.agh.iisg.topology.grid.interfaces.GridTopologyCreator;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GridTopologyCreatorImpl implements GridTopologyCreator {

    @Override
    public List<Neighbor> getTopology(Map<InetAddress, Integer> addresses) {
        List<Neighbor> neighbors = new ArrayList<>();
        neighbors.add(new NeighborImpl(new IdImpl(null, 1)));
        neighbors.add(new NeighborImpl(new IdImpl(null, 2)));
        neighbors.add(new NeighborImpl(new IdImpl(null, 3)));
        return neighbors;
    }

	@Override
	public List<Neighbor> createNeighbors(Map<InetAddress, Integer> addresses) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
