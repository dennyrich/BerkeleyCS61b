public class UnionFind {

    private int[] parents;
    private int[] sizes;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parents = new int[n];
        sizes = new int[n];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1; //all are disjointed, size = 1
            sizes[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex > parents.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        else if (vertex < 0) {
            throw new IllegalArgumentException("the size cannot be this big");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        /*
        int a = v1;
        while (a > 0) {
            a = parent(a);
        }
        return -1 * a;
        */
        validate(v1);
        if (parents[v1] < 0) {
            return sizes[v1];
        } else {
            return sizeOf(parents[v1]);
        }
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        if (parents[v1] < 0) {
            return -sizes[v1];
        }
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2) && (v1 != -1 && v2 != -1);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int smaller, larger;
        validate(v1);
        validate(v1);
        if (v1 == v2) {
            return;
        }
        if (sizeOf(v1) > sizeOf(v2)) {
            larger = v1;
            smaller = v2;
        } else {
            larger = v2;
            smaller = v1;
        }
        sizes[find(larger)] += sizes[find(smaller)];
        parents[find(smaller)] = find(larger);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (parents[vertex] < 0) {
            return vertex;
        }
        else {
            return find(parents[vertex]);
        }
    }

}
