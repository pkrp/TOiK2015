package pl.edu.agh.iisg.topology.helper.interfaces;

import java.util.Map;

import pl.edu.agh.iisg.topology.helper.IdImpl;

public interface Neighbor {

    public Map<String, Neighbor> getNeighborhood();

    public void addNeighbor(String description, Neighbor neighbor);

    public IdImpl getId();
}
