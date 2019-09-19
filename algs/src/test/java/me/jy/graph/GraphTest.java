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
    }

}
