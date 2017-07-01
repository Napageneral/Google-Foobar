package com.google.challenges;

/**
 * Created by Tyler Brandt on 6/12/2017.
 */
public class re_id {
        public static void main(String[] args) {
            int n = Integer.parseInt(args[0]);

            // initially assume all integers are prime
            boolean[] isPrime = new boolean[n+1];
            for (int i = 2; i <= n; i++) {
                isPrime[i] = true;
            }

            // mark non-primes <= n using re_id.Sieve of Eratosthenes
            for (int factor = 2; factor*factor <= n; factor++) {

                // if factor is prime, then mark multiples of factor as nonprime
                // suffices to consider mutiples factor, factor+1, ...,  n/factor
                if (isPrime[factor]) {
                    for (int j = factor; factor*j <= n; j++) {
                        isPrime[factor*j] = false;
                    }
                }
            }

            StringBuffer pool = new StringBuffer();
            for(int x = 2; x<=n;x++){
                if (isPrime[x]){
                    pool.append(x);
                }
            }

            // count primes
            int primes = 0;
            for (int i = 2; i <= n; i++) {
                if (isPrime[i]) primes++;
            }
            System.out.println("The number of primes <= " + n + " is " + primes);
            System.out.println(pool.length());

            System.out.println(answer(1415));
        }

        public static String answer(int n) {

            //re_id.Sieve of Eratosthenes
            int s = 21000;
            boolean[] isPrime = new boolean[s+1];
            for (int i = 2; i <=s; i++){
                isPrime[i] = true;
            }

            for(int factor = 2; factor*factor<=s; factor++){
                if(isPrime[factor]){
                    for(int j = factor; factor*j <= s; j++){
                        isPrime[factor*j]=false;
                    }
                }
            }

            int start = 2;

            StringBuffer pPool = new StringBuffer();
            int length = 0;
            while(length<10005){
                if(isPrime[start]){
                    pPool.append(String.valueOf(start));
                    int add = String.valueOf(start).length();
                    length = length+add;
                }
                start++;

            }

            String ID = pPool.substring(n,n+5);
            return ID;
        }
}
