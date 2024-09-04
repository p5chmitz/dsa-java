package smorgasbord.TGG;

import java.util.ArrayList;
import java.util.Arrays;

public class GTG01 {

    // Test rewrite for simple binary search algorithm ported from Rust code
    /** Iterative binary search method that returns -1 if the target is not found
     * Binary search is simple but inefficient, running in O(n^2) time.
     * */
    public static int binarySearch(int[] a, int key) {
        // Sets initial position of the search boundaries
        int left = 0;
        int right = a.length - 1;

        // Loops until the search boundaries overlap;
        // If the loop doesn't find the key, the function
        // returns None
        while (left <= right) {
            int mid = ( left + right ) / 2;
            if (a[mid] == key) {
                return mid;
            } else if (a[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            System.out.println("Guess index: " + mid);
        }
        return -1;
    }

    /** Simple insertion sort */
    public static int[] insertionSort(int[] data) {
        int n = data.length;
        for (int k = 1; k < n; k++) {
            int current = data[k];
            int j = k;
            while (j > 0 && data[j - 1] > current) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = current;
            //try {
            //    Thread.sleep(10);
            //} catch (InterruptedException e) {}
        }
        return data;
    }

    /** Extends Comparable to compare two parameters that are generic over T */
    public static <T extends Comparable<? super T>>
    void lemmeSee(T a, T b) {
        int n = a.compareTo(b);
        if (n < 0) {
            System.out.println("First one smol");
        } else if (n == 0) {
            System.out.println("Well pardner, thems is equal!");
        } else System.out.println("First one bigger man");
    }

    public static class Comp implements Comparable<Comp> {
        public int first;
        public int second;
        /** Compares two int values */
        public Comp(int a, int b) {
            first = a;
            second = b;
        }
        @Override
        public int compareTo(Comp o) {
            if ( this.first + this.second < o.first + o.second ) {
                return -1;
            } else if ( this.first + this.second == o.first + o.second ) {
                return 0;
            } else return 1;
        }
    }

    /** Runs an unsorted array through an insertion sort algorithm x times
     * and measures the execution time for comparison */
    public static void empiricalTest() {
        // Keep tracking of running time
        ArrayList<Long> runningTime = new ArrayList<>();

        // An array to be sorted with a O(n^2) insertion sort
        int[] unsorted = {1, 5, 7, 3, 5, 6, 7, 8, 8, 12, 23, 23, 24, 25};

        for (int i = 1; i <= 10; i++ ) {
            long startTime = System.nanoTime();
            int[] sorted = insertionSort(unsorted);
            //long endTime = System.currentTimeMillis();
            long endTime = System.nanoTime();

            if (i == 1)
                System.out.println(Arrays.toString(sorted));

            // Calculates and adds completionTime for average calculation
            long completionTime = endTime - startTime;
            runningTime.add(completionTime);
            System.out.println("Sorting took " + completionTime + " nanoseconds");
        }

        // Calculates and prints average running time
        double avg = runningTime.get(0);
        for (int i = 1; i < runningTime.size(); i++) {
            avg += runningTime.get(i);
        }
        avg = avg / runningTime.size();
        System.out.println("With an average of " + avg + " nanoseconds");
    }

}
