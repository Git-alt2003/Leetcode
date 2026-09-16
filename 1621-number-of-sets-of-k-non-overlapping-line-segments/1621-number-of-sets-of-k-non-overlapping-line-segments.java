class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int N = n + k - 1;
        int R = 2 * k;
        
       
        long[] fact = new long[N + 1];
        long[] invFact = new long[N + 1];
        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[N] = modPow(fact[N], MOD - 2, MOD);
        for (int i = N; i > 0; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }
        
        long result = fact[N] * invFact[R] % MOD * invFact[N - R] % MOD;
        return (int) result;
    }
    
    private long modPow(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return res;
        
    }
}