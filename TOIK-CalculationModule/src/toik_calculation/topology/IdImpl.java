package toik_calculation.topology;

import java.net.InetAddress;

public class IdImpl implements Id {

    private InetAddress address;

    private Integer id;

    public IdImpl(InetAddress address, Integer id) {
        this.address = address;
        this.id = id;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getId() {
        return toString();
    }

    @Override
    public String toString() {
        return  address.toString() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdImpl id1 = (IdImpl) o;

        if (address != null ? !address.equals(id1.address) : id1.address != null) return false;
        if (id != null ? !id.equals(id1.id) : id1.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
