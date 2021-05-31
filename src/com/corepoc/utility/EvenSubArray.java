package com.corepoc.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class EvenSubArray {

    public static void main(String[] args) {
        System.out.println("Hello");
        //evenSubarray(Arrays.asList(1,2,3,4), 1);
        int a[] = { 2, 2, 5, 6, 9, 2, 11 };
        System.out.println(countSubarrays(a, a.length, 1));
        countSubarrays(a,4, 1);
    }

    public static int evenSubarray(List<Integer> numbers, int k) {
        int allMasks = 1 << numbers.size();
        List<List<Integer>> output = new ArrayList<List<Integer>>();

        for (int i = 0; i < allMasks; i++) {
            List<Integer> sub = new ArrayList<Integer>();
            Set<Integer> set = null;

            for (int j = 0; j < numbers.size(); j++) {
                if ((i & (1 << j)) > 0) {
                    sub.add(numbers.get(j));
                }
            }
            if (!sub.isEmpty() && sub.stream().filter(current -> current % 2 != 0).count() <= k) {
                output.add(sub);
            }
        }
        System.out.println(output);
        return output.size();

    }

    public static int evenSubarrayNew(List<Integer> numbers, int k) {
        int allMasks = 1 << numbers.size();
        List<List<Integer>> output = new ArrayList<List<Integer>>();

        for (int i = 0; i < allMasks; i++) {
            List<Integer> sub = new ArrayList<Integer>();
            Set<Integer> set = null;

            for (int j = 0; j < numbers.size(); j++) {
                if ((i & (1 << j)) > 0) {
                    sub.add(numbers.get(j));
                }
            }
            if (!sub.isEmpty() && sub.stream().filter(current -> current % 2 != 0).count() <= k) {
                output.add(sub);
            }
        }
        System.out.println(output);
        return output.size();

    }

    // Java program to count the
// number of subarrays with
// m odd numbers




        // function that returns the count of
        // subarrays with m odd numbers
        //
        public static int countSubarrays(int a[], int n, int m)
        {
            int count = 0;
            int prefix[] = new int[n + 1];
            int odd = 0;

            // Traverse in the array
            for (int i = 0; i < n; i++)
            {
                prefix[odd]++;

                // If array element is odd
                if ((a[i] & 1) == 1)
                    odd++;

                // When number of odd
                // elements >= M
                if (odd >= m)
                    count += prefix[odd - m];
            }

            return count;
        }

        // Driver code
        /*public static void main(String[] args)
        {
            int a[] = { 2, 2, 5, 6, 9, 2, 11 };
            int n = a.length;
            int m = 2;

            // Function call
            System.out.println(countSubarrays(a, n, m));
        }*/


// This code is contributed by akash1295.

}
