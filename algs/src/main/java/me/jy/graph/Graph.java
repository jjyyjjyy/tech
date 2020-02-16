package me.jy.graph;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class Graph {

    // 顶点数
    private int v;

    // 边数
    private int e;

    private Map<Integer, List<Integer>> adj;

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedHashMap<>();
        IntStream.range(0, v)
            .forEach(i -> this.adj.put(i, new ArrayList<>()));
    }

    /**
     * 获取与顶点v相邻的顶点
     */
    public Iterable<Integer> adj(int v) {
        return Optional.ofNullable(adj.get(v)).orElse(Collections.emptyList());
    }

    /**
     * 顶点a和顶点b之间添加一条边
     */
    public void addEdge(int a, int b) {
        if (!adj.containsKey(a) || !adj.containsKey(b)) {
            throw new RuntimeException("No such point!");
        }
        adj.get(a).add(b);
        adj.get(b).add(a);
        e++;
    }

    /**
     * 计算顶点v的度数
     *
     * @return 度数
     */
    public int degree(int v) {
        Iterable<Integer> adj = adj(v);
        int degree = 0;
        for (Integer ignored : adj) {
            degree++;
        }
        return degree;
    }

    /**
     * 获取图中顶点的最大度数
     */
    public int maxDegree() {
        int max = 0;
        for (int i = 0; i < v; i++) {
            int degree = degree(i);
            if (degree > max) {
                max = degree;
            }
        }
        return max;
    }

    /**
     * 获取图中顶点的平均度数
     */
    public double avgDegree() {
        return 2.0 * e / v;
    }

    /**
     * 获取自环个数
     */
    public int selfLoops() {
        int sum = 0;
        for (int i = 0; i < v; i++) {
            for (Integer p : adj(v)) {
                if (p == i) {
                    sum++;
                }
            }
        }
        return sum / 2;
    }

    /**
     * 判断两个顶点是否相连
     */
    public boolean marked(int v, int s) {
        if (!adj.containsKey(v)) {
            return false;
        }
        return adj.get(v).contains(s);
    }

    /**
     * 获取与顶点v相邻的顶点数
     */
    public int count(int v) {
        return Optional.ofNullable(adj.get(v)).orElse(Collections.emptyList()).size();
    }

    public void dfs(Consumer<Integer> pointConsumer, int p) {
        if (!adj.containsKey(p)) {
            return;
        }
        boolean[] visited = new boolean[v];
        dfs(pointConsumer, p, visited);
    }

    public void dfs(Consumer<Integer> pointConsumer, int p, boolean[] visited) {
        if (visited[p]) {
            return;
        }
        visited[p] = true;
        pointConsumer.accept(p);
        for (Integer item : adj.get(p)) {
            dfs(pointConsumer, item, visited);
        }
    }

    public void bfs(Consumer<Integer> pointConsumer, int p) {
        if (!adj.containsKey(p)) {
            return;
        }
        boolean[] visited = new boolean[v];
        bfs(pointConsumer, p, visited);
    }

    private void bfs(Consumer<Integer> pointConsumer, int p, boolean[] visited) {
        if (visited[p]) {
            return;
        }
        pointConsumer.accept(p);
        visited[p] = true;

        Queue<Integer> todo = new LinkedList<>(adj.get(p));

        while (!todo.isEmpty()) {
            Integer next = todo.remove();
            if (visited[next]) {
                continue;
            }
            pointConsumer.accept(next);
            visited[next] = true;

            todo.addAll(adj.get(next));
        }

    }

    public List<Integer> dfsPath(int end) {
        if (!adj.containsKey(end)) {
            return Collections.emptyList();
        }

        Stack<Integer> path = new Stack<>();
        return null;
    }

    /**
     * 获取顶点数
     */
    public int v() {
        return v;
    }

    /**
     * 获取边数
     */
    public int e() {
        return e;
    }
}
