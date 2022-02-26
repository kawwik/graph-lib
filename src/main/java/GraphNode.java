import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GraphNode<TValue> {
    private ArrayList<GraphNode<TValue>> _neighbours = new ArrayList<>();
    private TValue _value;

    public GraphNode(TValue value) {
        _value = value;
    }

    public Collection<GraphNode<TValue>> get_neighbours() {
        return Collections.unmodifiableCollection(_neighbours);
    }

    public Integer CurrentBfsLabel;
    public Integer CurrentDfsLabel;
    public boolean IsVisited;

    public void addNeighbour(GraphNode<TValue> neighbour) {
        _neighbours.add(neighbour);
    }
}
