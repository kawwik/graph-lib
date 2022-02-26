import java.util.*;

public class Graph<TValue> {
    private ArrayList<GraphNode<TValue>> _nodes = new ArrayList<>();

    public Collection<GraphNode<TValue>> get_nodes() {
        return Collections.unmodifiableCollection(_nodes);
    }

    public void addNode(GraphNode<TValue> node) {
        if (node == null)
            throw new NullPointerException("Node can't be null.");

        _nodes.add(node);
    }

    public void addEdge(GraphNode<TValue> node1, GraphNode<TValue> node2) {
        if (!_nodes.contains(node1) || !_nodes.contains(node2))
            throw new IllegalArgumentException("One of the nodes doesn't belong to graph.");

        node1.addNeighbour(node2);
        node2.addNeighbour(node1);
    }
    
    private void setNodesNotVisited() {
        _nodes.forEach(node -> node.IsVisited = false);
    }

    public void ResetLabels() {
        _nodes.forEach(node -> {
            node.CurrentBfsLabel = null;
            node.CurrentDfsLabel = null;
        });
    }

    public void RunBfs(GraphNode<TValue> start)
    {
        ResetLabels();

        var queue = new ArrayDeque<GraphNode<TValue>>();
        queue.addLast(start);

        int label = 0;
        while (queue.size() != 0)
        {
            var current = queue.getFirst();
            queue.removeFirst();
            current.IsVisited = true;
            current.CurrentBfsLabel = label++;

            for (var neighbour : current.get_neighbours())
            {
                if (!neighbour.IsVisited)
                    queue.addLast(neighbour);
            }
        }

        setNodesNotVisited();
    }

    private int RunDfsStep(GraphNode<TValue> current, int label)
    {
        current.IsVisited = true;
        current.CurrentDfsLabel = label++;

        for (var neighbour : current.get_neighbours())
        {
            if (!neighbour.IsVisited)
                label = RunDfsStep(current, label);
        }

        return label;
    }

    public void RunDfs(GraphNode<TValue> start)
    {
        ResetLabels();

        int label = 0;
        RunDfsStep(start, label);

        setNodesNotVisited();
    }
}
