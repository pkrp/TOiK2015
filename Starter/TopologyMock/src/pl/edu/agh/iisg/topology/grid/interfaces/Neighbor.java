package pl.edu.agh.iisg.topology.grid.interfaces;

import java.util.Map;

import pl.edu.agh.iisg.topology.grid.IdImpl;
import pl.edu.agh.iisg.topology.grid.interfaces.Neighbor;

public interface Neighbor {

    public Map<String, Neighbor> getNeighborhood();

    public void addNeighbor(String description, Neighbor neighbor);

    public IdImpl getId();
}
