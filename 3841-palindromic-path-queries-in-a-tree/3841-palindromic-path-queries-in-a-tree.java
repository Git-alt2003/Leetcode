class Solution {
    int n;
    List<Integer>[] adj;
    int[] parent, depth, heavy, head, pos, sz;
    int[] maskBase;      
    int[] seg;          
    char[] ch;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        ch = s.toCharArray();
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        sz = new int[n];
        Arrays.fill(heavy, -1);
        Arrays.fill(parent, -1);


        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, cnt = 0;
        stack[top++] = 0;
        parent[0] = -1;
        while (top > 0) {
            int u = stack[--top];
            order[cnt++] = u;
            for (int v : adj[u]) {
                if (v != parent[u]) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[top++] = v;
                }
            }
        }
    
        for (int i = cnt - 1; i >= 0; i--) {
            int u = order[i];
            sz[u] = 1;
            int best = 0;
            for (int v : adj[u]) {
                if (v != parent[u]) {
                    sz[u] += sz[v];
                    if (sz[v] > best) {
                        best = sz[v];
                        heavy[u] = v;
                    }
                }
            }
        }
    
        int cur = 0;
        int[] nodeAt = new int[n];
        for (int i = 0; i < cnt; i++) {
            int u = order[i];
          
            if (u == 0 || heavy[parent[u]] != u) {
                int x = u;
                while (x != -1) {
                    head[x] = u;
                    pos[x] = cur;
                    nodeAt[cur] = x;
                    cur++;
                    x = heavy[x];
                }
            }
        }

        maskBase = new int[n];
        for (int i = 0; i < n; i++) maskBase[pos[i]] = 1 << (ch[i] - 'a');

        seg = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                ch[u] = c;
                update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int m = pathMask(u, v);
                ans.add(Integer.bitCount(m) <= 1);
            }
        }
        return ans;
    }

    int pathMask(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) { int t = u; u = v; v = t; }
            res ^= query(1, 0, n - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) { int t = u; u = v; v = t; }
        res ^= query(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }

    void build(int node, int l, int r) {
        if (l == r) { seg[node] = maskBase[l]; return; }
        int mid = (l + r) >>> 1;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        seg[node] = seg[node * 2] ^ seg[node * 2 + 1];
    }

    void update(int node, int l, int r, int idx, int val) {
        if (l == r) { seg[node] = val; return; }
        int mid = (l + r) >>> 1;
        if (idx <= mid) update(node * 2, l, mid, idx, val);
        else update(node * 2 + 1, mid + 1, r, idx, val);
        seg[node] = seg[node * 2] ^ seg[node * 2 + 1];
    }

    int query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;
        if (ql <= l && r <= qr) return seg[node];
        int mid = (l + r) >>> 1;
        return query(node * 2, l, mid, ql, qr) ^ query(node * 2 + 1, mid + 1, r, ql, qr);
    }
}

        
   
