/**
 * Edge Class. Has info about edge capacity
 */
public class Edge {

    private Vertex vertexTo;
    private int capacity;
    private int flow;

    public Edge(Vertex to, int capacity) {
        this.vertexTo = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    static void createEdge(Vertex from, Vertex to, int capacity) {
        Edge forward = new Edge(to, capacity);
        Edge back = new Edge(from, 0);  // For undirected, also set this vertexTo capacity
        forward.setFlow(0);
        back.setFlow(forward.getCapacity());
        from.addEdge(forward);
        to.addEdge(back);
    }

    public Vertex getVertexTo() {
        return vertexTo;
    }

    public void setVertexTo(Vertex vertexTo) {
        this.vertexTo = vertexTo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertexTo=" + vertexTo.getName() +
                ", c=" + capacity +
                ", f=" + flow +
                '}';
    }
}