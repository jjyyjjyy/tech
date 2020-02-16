package me.jy.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 */
class GraphTest {

    @Test
    void testDfs() {
        Graph graph = new Graph(10);
        List<Integer> edge = new ArrayList<>();
        graph.dfs(edge::add, 0);
        Assertions.assertEquals(1, edge.size());
        edge.clear();
        graph.addEdge(0, 9);
        graph.dfs(edge::add, 0);
        Assertions.assertEquals(2, edge.size());

        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(9, 1);
        graph.addEdge(9, 2);
        edge.clear();
        graph.bfs(edge::add, 0);
        Assertions.assertArrayEquals(new int[]{0, 9, 1, 3, 2}, edge.stream().mapToInt(a -> a).toArray());

        edge.clear();
        graph.dfs(edge::add, 0);
        Assertions.assertArrayEquals(new int[]{0, 9, 1, 2, 3}, edge.stream().mapToInt(a -> a).toArray());
    }

}
