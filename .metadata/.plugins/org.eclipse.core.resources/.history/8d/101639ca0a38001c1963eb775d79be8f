/*  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class DirectedDFS {
    private boolean[] marked;  // marked[v] = true iff v is reachable from source(s)
    private int count;         // number of vertices reachable from source(s)

    /**
     * Computes the vertices in DAG {@code G} that are
     * reachable from the source vertex {@code s}.
     * @param G the DAG
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DirectedDFS(DAG G, int s) {
        marked = new boolean[G.V()];
//        validateVertex(s);
        dfs(G, s);
    }

    /**
     * Computes the vertices in DAG {@code G} that are
     * connected to any of the source vertices {@code sources}.
     * @param G the graph
     * @param sources the source vertices
     * @throws IllegalArgumentException if {@code sources} is {@code null}
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     *         for each vertex {@code s} in {@code sources}
     */
    public DirectedDFS(DAG G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
//        validateVertices(sources);
        for (int v : sources) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(DAG G, int v) { 
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    /**
     * Is there a directed path from the source vertex (or any
     * of the source vertices) and vertex {@code v}?
     * @param  v the vertex
     * @return {@code true} if there is a directed path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
//        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of vertices reachable from the source vertex
     * (or source vertices).
     * @return the number of vertices reachable from the source vertex
     *   (or source vertices)
     */
    public int count() {
        return count;
    }

}