public class UnionFind {

    private int[] holder;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        holder = new int[n];
        for (int i = 0; i < holder.length; i++) {
            holder[i] = -1; //all are disjointed, size = 1
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex > holder.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        else if (vertex < 0 && vertex > -1 * holder.length) {
            throw new IllegalArgumentException("the size cannot be this big");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int a = v1;
        while (a > 0) {
            a = parent(a);
        }
        return -1 * a;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return holder[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int smaller, larger;
        if (sizeOf(v1) > sizeOf(v2)) {
            larger = v1;
            smaller = v2;
        } else {
            larger = v2;
            smaller = v1;
        }
        holder[find(smaller)] = holder[find(larger)];
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int v = vertex;
        while (parent(v) > 0) {
            v = parent(v);
        }
        return v;
    }

}