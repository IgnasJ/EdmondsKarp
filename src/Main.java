import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Ignas on 2016-12-05.
 */
public class Main {
    private static int maxFlow = 0;

    public static void main(String[] args) {
        System.out.println("Edmond Karp algorithm!");

        Vertex s = new Vertex(0, "s");
        Vertex v1 = new Vertex(1, "v1");
        Vertex v2 = new Vertex(2, "v2");
        Vertex v3 = new Vertex(3, "v3");
        Vertex v4 = new Vertex(4, "v4");
        Vertex t = new Vertex(5, "t");
        s.setStart(true);
        t.setEnd(true);
        Edge.createEdge(s, v1, 16);
        Edge.createEdge(s, v2, 13);
        Edge.createEdge(v1, v3, 12);
        Edge.createEdge(v2, v1, 4);
        Edge.createEdge(v2, v4, 14);
        Edge.createEdge(v3, v2, 9);
        Edge.createEdge(v3, t, 20);
        Edge.createEdge(v4, v3, 7);
        Edge.createEdge(v4, t, 4);
        Graph graph = new Graph(new ArrayList<>(Arrays.asList(s, v1, v2, v3, v4, t)));

        while(true) {
            ArrayList<Vertex> path = EdmondsKarp(graph);
            if (path.get(0).getWidth() == 0) break;

            System.out.println("MAX FLOW PATH " + path);
            //Gaunam maziausia width
            int minimumWidthAfterDijiksta = Integer.MAX_VALUE;
            for (Vertex aPath : path) {
                int vertexWidth = aPath.getWidth();
                if (vertexWidth < minimumWidthAfterDijiksta) {
                    minimumWidthAfterDijiksta = vertexWidth;
                }
            }

            System.out.println("Minimum width after step: " + minimumWidthAfterDijiksta);
            maxFlow += minimumWidthAfterDijiksta;

            for (Vertex vertex : path) {
                Vertex parent = vertex.getPrevious();
                if (parent != null) {
                    for (Edge edge : parent.getEdges()) {
                        if (edge.getVertexTo() == vertex) {
                            edge.setFlow(edge.getFlow() + minimumWidthAfterDijiksta);
                            edge.setCapacity(edge.getCapacity() - minimumWidthAfterDijiksta);
                            for (Edge vertexEdge : vertex.getEdges()) {
                                if (vertexEdge.getVertexTo() == parent) {
                                    vertexEdge.setFlow(vertexEdge.getFlow() - minimumWidthAfterDijiksta);
                                    vertexEdge.setCapacity(vertexEdge.getCapacity() + minimumWidthAfterDijiksta);
                                }
                            }
                        }
                    }
                }
            }
            path.clear();
        }

        System.out.println("Ats.: " + maxFlow);
    }

    /**
     * Function that uses Edmonds Karp algorithm to find path from s to t with maximum flow
     * @param graph
     * @return List of Vertex
     */
    private static ArrayList<Vertex> EdmondsKarp(Graph graph) {

        for (Vertex vertex : graph.getVertexList()) {
            vertex.setWidth(Integer.MIN_VALUE);
            vertex.setPrevious(null);
            if (vertex.isStart()) {
                vertex.setWidth(Integer.MAX_VALUE);
            }
        }

        ArrayList qList = new ArrayList(graph.getVertexList());

        while (qList.size() > 0) {
            Vertex u = (Vertex) qList.get(0);
            for (int i = 1; i < qList.size(); ++i) {
                Vertex tempVertex = (Vertex) qList.get(i);
                if (tempVertex.getWidth() > u.getWidth()) {
                    u = tempVertex;
                }
            }

            qList.remove(u);

            if (u.getWidth() == Integer.MIN_VALUE) {
                break;
            }
            for (Edge edge : u.getEdges()) {
                int temp = Math.max(edge.getVertexTo().getWidth(), Math.min(u.getWidth(), edge.getCapacity()));
                if (temp > edge.getVertexTo().getWidth()) {
                    edge.getVertexTo().setWidth(temp);
                    edge.getVertexTo().setPrevious(u);
                }
            }
        }

        ArrayList<Vertex> pathWithMaximumWidth = new ArrayList();
        Vertex previous = graph.getVertexList().get(graph.getVertexList().size() - 1);
        while (previous != null) {
            pathWithMaximumWidth.add(previous);
            previous = previous.getPrevious();
        }

        return pathWithMaximumWidth;
    }
}
