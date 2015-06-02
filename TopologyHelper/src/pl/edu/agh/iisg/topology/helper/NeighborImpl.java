package pl.edu.agh.iisg.topology.helper;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.iisg.topology.helper.interfaces.Neighbor;

public class NeighborImpl implements Neighbor {

    private Map<String, Neighbor> neighborhood;
    private IdImpl id;

    public NeighborImpl(IdImpl id) {
        this.id = id;
        this.neighborhood = new HashMap<String, Neighbor>();
    }

    public Map<String, Neighbor> getNeighborhood() {
        return neighborhood;
    }

    public void addNeighbor(String description, Neighbor neighbor) {
        this.neighborhood.put(description, neighbor);
    }

    public IdImpl getId() {
    	return id;
    }
}
