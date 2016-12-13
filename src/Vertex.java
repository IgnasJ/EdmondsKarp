import java.util.ArrayList;
import java.util.List;

/**
 * Vertex Class. Has List of Edges
 */
public class Vertex {
    private int index;
    private String name;
    private boolean start, end; // Is this the source or sink?
    private int width;
    private Vertex previous;
    private List<Edge> edges = new ArrayList<Edge>();

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }


    public Vertex(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public String toString() {
        return '\n' + "Vertex{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", width=" + width +
                '}';
    }
}
