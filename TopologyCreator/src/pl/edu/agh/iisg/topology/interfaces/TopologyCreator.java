package pl.edu.agh.iisg.topology.interfaces;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pl.edu.agh.iisg.topology.IdImpl;
import pl.edu.agh.iisg.topology.NeighborImpl;

public abstract class TopologyCreator {

    public abstract List<Neighbor> getTopology(Map<InetAddress, Integer> addresses);

    public List<Neighbor> createNeighbors(Map<InetAddress, Integer> addresses) {
        List<Neighbor> neighbors = new ArrayList<>();
        
        for(Entry<InetAddress, Integer> entry: addresses.entrySet()) {
        	neighbors.add(new NeighborImpl(new IdImpl(entry.getKey(), entry.getValue())));
        }
        return neighbors;
    }
}
