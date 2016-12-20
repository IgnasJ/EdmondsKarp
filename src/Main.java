import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    private static int maxFlow = 0;

    public static void main(String[] args) {
        System.out.println("Edmonds Karp algorithm!");

        Vertex s = new Vertex(0, "s");
        Vertex a = new Vertex(1, "a");
        Vertex b = new Vertex(2, "b");
        Vertex c = new Vertex(3, "c");
        Vertex i = new Vertex(4, "i");
        Vertex j = new Vertex(5, "j");
        Vertex k = new Vertex(6, "k");
        Vertex t = new Vertex(7, "t");

        Edge.createEdge(s, a, 6);
        Edge.createEdge(s, b, 10);
        Edge.createEdge(s, c, 3);
        Edge.createEdge(a, i, 4);
        Edge.createEdge(a, j, 1);
        Edge.createEdge(b, i, 5);
        Edge.createEdge(b, j, 10);
        Edge.createEdge(c, j, 2);
        Edge.createEdge(c, k, 4);
        Edge.createEdge(i, t, 9);
        Edge.createEdge(i, j, 8);
        Edge.createEdge(j, t, 20);
        Edge.createEdge(j, k, 7);
        Edge.createEdge(k, t, 7);

        s.setStart(true);
        t.setEnd(true);

        Graph graph = new Graph(new ArrayList<>(Arrays.asList(s, a, b, c, i, j, k, t)));

        while (true) {
            ArrayList<Vertex> path = EdmondsKarp(graph);
            if (path.get(0).getWidth() == 0) break;

            printPathThatContainsVertex(path, a);

            //System.out.println("MAX FLOW PATH " + path);
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

    private static void printPathThatContainsVertex(ArrayList<Vertex> path, Vertex v) {
        if (path.contains(v))
            System.out.println("Papildomas takas, turintis viršūnę "+ v.getName() + " :"+ path);
    }

    /**
     * Function that uses Edmonds Karp algorithm to find path from s to t with maximum flow
     *
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
