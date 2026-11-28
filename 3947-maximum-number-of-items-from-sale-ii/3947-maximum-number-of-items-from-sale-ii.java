import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] multCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            int sum = 0;
            for (int mult = f; mult <= n; mult += f) {
                sum += freq[mult];
            }
            multCount[f] = sum;
        }

        // Pack (price << 32) | idx into a primitive long array to avoid Integer boxing
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) {
            packed[i] = (((long) items[i][1]) << 32) | (i & 0xffffffffL);
        }
        Arrays.sort(packed);

        long remBudget = budget;
        long totalItems = 0;

        for (long entry : packed) {
            int idx = (int) entry;
            long price = entry >>> 32;
            int g = Math.max(0, multCount[items[idx][0]] - 1);

            if (g == 0 || 2L * minPrice <= price) {
                continue;
            }
            if (remBudget < price) {
                break;
            }

            long buyCount = Math.min((long) g, remBudget / price);
            remBudget -= buyCount * price;
            totalItems += buyCount * 2L;
        }

        if (remBudget >= minPrice) {
            totalItems += remBudget / minPrice;
        }

        return (int) totalItems;
    }
}

        

