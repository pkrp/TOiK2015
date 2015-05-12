package pl.edu.agh.iisg.topology.interfaces;

import pl.edu.agh.iisg.topology.IdImpl;
import pl.edu.agh.iisg.topology.NeighborImpl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TopologyCreator {

    public List<Neighbor> getTopology(Map<InetAddress, Integer> addresses);

    public default List<Neighbor> createNeighbors(Map<InetAddress, Integer> addresses) {
        List<Neighbor> neighbors = new ArrayList<>();

        addresses
            .entrySet()
            .stream()
            .forEach( entry -> {
                neighbors.add(new NeighborImpl(new IdImpl(entry.getKey(), entry.getValue())));
            });

        return neighbors;
    }
}
