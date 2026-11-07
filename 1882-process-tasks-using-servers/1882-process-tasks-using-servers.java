import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Min-heap for available servers: [weight, index]
        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Min-heap for unavailable servers: [free_time, weight, index] -> stored as long[]
        PriorityQueue<long[]> unavailable = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        long currentTime = 0;
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // If no servers are available, jump time to when the next server frees up
            if (available.isEmpty()) {
                currentTime = unavailable.peek()[0];
            }

            // Move all servers that have finished their tasks back to 'available'
            while (!unavailable.isEmpty() && unavailable.peek()[0] <= currentTime) {
                long[] freed = unavailable.poll();
                available.offer(new int[]{(int) freed[1], (int) freed[2]}); // [weight, index]
            }

            // Assign the current task to the best available server
            int[] server = available.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;
            unavailable.offer(new long[]{currentTime + tasks[j], weight, index});
        }

        return ans;
    }
}
 
        
    
