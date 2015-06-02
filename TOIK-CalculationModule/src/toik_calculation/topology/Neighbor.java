package toik_calculation.topology;

import java.util.Map;

public interface Neighbor {

    public Map<String, Neighbor> getNeighborhood();

    public void addNeighbor(String description, Neighbor neighbor);

    public Id getId();
}
