class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
   
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
        }


 
        long INF = Long.MAX_VALUE;
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) Arrays.fill(row, INF);


        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long cost = cur[0];
            int u = (int) cur[1];
            int run = (int) cur[2];

            if (cost > dist[u][run]) continue;
            if (u == n - 1) return (int) cost;

            for (int[] nb : adj.get(u)) {
                int v = nb[0];
                long w = nb[1];
                int nrun;
                if (labels.charAt(v) == labels.charAt(u)) {
                    nrun = run + 1;
                    if (nrun > k) continue;
                } else {
                    nrun = 1;
                }
                long nc = cost + w;
                if (nc < dist[v][nrun]) {
                    dist[v][nrun] = nc;
                    pq.offer(new long[]{nc, v, nrun});
                }
            }
        }

        return -1;
    }
}

        
   
