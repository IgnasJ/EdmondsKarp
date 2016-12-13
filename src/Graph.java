import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ignas on 2016-11-23.
 */

/**
 * Graph class. Has List of Vertix
 */
public class Graph {
    List<Vertex> vertexList = new ArrayList<>();

    public Graph(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertexList=" + vertexList +
                '}';
    }
}
