import java.util.*;

class Solution {
    List<Integer>[] children;
    List<Integer>[] newChildren;
    int[] newParent;
    int[] last;
    int[] answer;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        children = new ArrayList[n];
        newChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        newParent = parent.clone();
        last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, s);

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        answer = new int[n];
        calculateSize(0);

        return answer;
    }

    private void dfs(int node, String s) {
        int c = s.charAt(node) - 'a';
        int previous = last[c];

        if (previous != -1) {
            newParent[node] = previous;
        }

        last[c] = node;

        for (int child : children[node]) {
            dfs(child, s);
        }

        last[c] = previous;
    }

    private int calculateSize(int node) {
        int size = 1;

        for (int child : newChildren[node]) {
            size += calculateSize(child);
        }

        answer[node] = size;
        return size;
    }
}

        
   
