package pl.edu.agh.iisg.topology.ring;

import pl.edu.agh.iisg.topology.ring.interfaces.Neighbor;
import pl.edu.agh.iisg.topology.ring.interfaces.RingTopologyCreator;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RingTopologyCreatorImpl implements RingTopologyCreator {


    @Override
    public List<Neighbor> getTopology(Map<InetAddress, Integer> addresses) {

    	List<Neighbor> neighbors = createNeighbors(addresses);

        for(int i=0;i<neighbors.size()-1;i++) {
            neighbors.get(i).addNeighbor("next", neighbors.get(i+1));
        }

        neighbors.get(neighbors.size()-1).addNeighbor("next", neighbors.get(0));

        return neighbors;
    }
    
    @Override
    public List<Neighbor> createNeighbors(Map<InetAddress, Integer> addresses) {
        List<Neighbor> neighbors = new ArrayList<>();
        for(Entry<InetAddress, Integer> entry: addresses.entrySet()) {
        	for(int i = 0; i < entry.getValue(); i++) {
        		neighbors.add(new NeighborImpl(new IdImpl(entry.getKey(), i)));
        	}
        }
        return neighbors;
    }
}
