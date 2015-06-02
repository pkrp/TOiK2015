package pl.edu.agh.iisg.topology.grid.interfaces;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pl.edu.agh.iisg.topology.grid.interfaces.Id;
import pl.edu.agh.iisg.topology.grid.interfaces.Neighbor;

public interface GridTopologyCreator {

    public List<Neighbor> getTopology(Map<InetAddress, Integer> addresses);

    public List<Neighbor> createNeighbors(Map<InetAddress, Integer> addresses);
}
