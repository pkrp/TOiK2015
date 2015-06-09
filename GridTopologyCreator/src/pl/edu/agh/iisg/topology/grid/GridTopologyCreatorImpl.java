package pl.edu.agh.iisg.topology.grid;

import pl.edu.agh.iisg.topology.grid.interfaces.Neighbor;
import pl.edu.agh.iisg.topology.grid.interfaces.GridTopologyCreator;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GridTopologyCreatorImpl implements GridTopologyCreator {

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.iisg.topology.grid.interfaces.GridTopologyCreator#getTopology(java.util.Map)
	 * Map<InetAddress, Integer> addresses - map of adresses of each workplace with amount of available agents
	 */
    @Override
    public List<Neighbor> getTopology(Map<InetAddress, Integer> addresses) {
        List<Neighbor> neighbors = createNeighbors(addresses);
    	int gridSize = (int) Math.ceil(Math.sqrt(neighbors.size()));

        for(int i = 0; i < neighbors.size(); i++) {
            if (i % gridSize != 0) {
            	neighbors.get(i).addNeighbor("left", neighbors.get(i - 1));
            }
            if (i % gridSize != gridSize - 1 && i < neighbors.size() - 1) {
            	neighbors.get(i).addNeighbor("right", neighbors.get(i + 1));
            }
            if (i - gridSize >= 0) {
            	neighbors.get(i).addNeighbor("top", neighbors.get(i - gridSize));
            }
            if (i + gridSize <= neighbors.size() - 1) {
            	neighbors.get(i).addNeighbor("bottom", neighbors.get(i + gridSize));
            }

        }

        neighbors.get(neighbors.size()-1).addNeighbor("next", neighbors.get(0));

        return neighbors;
    }
        
    private List<Neighbor> createNeighbors(Map<InetAddress, Integer> addresses) {
        List<Neighbor> neighbors = new ArrayList<>();
        
        for(Entry<InetAddress, Integer> entry: addresses.entrySet()) {
        	for(int i = 0; i < entry.getValue(); i++) {
        		neighbors.add(new NeighborImpl(new IdImpl(entry.getKey(), i)));
        	}
        }
        return neighbors;
    }
}
