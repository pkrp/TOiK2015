package pl.edu.agh.iisg.topology.interfaces;

import pl.edu.agh.iisg.topology.IdImpl;

import java.util.Map;

public interface Neighbor {

    public Map<String, Neighbor> getNeighborhood();

    public void addNeighbor(String description, Neighbor neighbor);

    public IdImpl getId();
}
