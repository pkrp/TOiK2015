package pl.edu.agh.iisg.topology;

import pl.edu.agh.iisg.topology.interfaces.Neighbor;
import pl.edu.agh.iisg.topology.interfaces.TopologyCreator;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

public class RingTopologyCreator implements TopologyCreator {

    @Override
    public List<Neighbor> getTopology(Map<InetAddress, Integer> addresses) {

        List<Neighbor> neighbors = createNeighbors(addresses);

        for(int i=0;i<neighbors.size()-1;i++) {
            neighbors.get(i).addNeighbor("next", neighbors.get(i+1));
        }

        neighbors.get(neighbors.size()-1).addNeighbor("next", neighbors.get(0));

        return neighbors;
    }
}
