package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.FileUtil;
import edu.neu.coe.info6205.util.SortUtils;

import java.nio.file.Paths;
import java.util.List;

public class DualPivotQuickSort {

    private static String[] words;
    private static int length;

    public static void main(String[] args) {
        FileUtil fu = new FileUtil();
        String[] a = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "demo.txt").toString()).toArray(new String[0]);
        sort(a);
        fu.writeFile(List.of(a), Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "demo.txt").toString());
    }

    public static void sort(String[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        words = array;
        length = array.length;
        sort(0, length - 1);
        assert isSorted();

        for (String s : words) {
            System.out.println(s);
        }
    }

    private static void sort(int low, int high) {

        if (low < high) {
            int[] piv;
            piv = partition(low, high);

            sort(low, piv[0] - 1);
            sort( piv[0] + 1, piv[1] - 1);
            sort( piv[1] + 1, high);
        }
    }

    static int[] partition(int low, int high) {
        if (words[low].compareToIgnoreCase(words[high]) > 0)
            swap( low, high);

        int j = low + 1, g = high - 1, k = low + 1;
        String p = words[low], q = words[high];

        while (k <= g) {

            if (words[k].compareToIgnoreCase(p)<0) {
                swap( k, j);
                j++;
            }

            else if (words[k].compareToIgnoreCase(q)>=0) {
                while (words[g].compareToIgnoreCase(q)>0 && k < g)
                    g--;

                swap( k, g);
                g--;

                if (words[k].compareToIgnoreCase(p)<0) {
                    swap( k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        swap(low, j);
        swap(high, g);

        return new int[] { j, g };
    }


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(String v, String w) {
        return v.compareToIgnoreCase(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void swap(int i, int j) {
        String swap = words[i];
        words[i] = words[j];
        words[j] = swap;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted() {
        return isSorted(words, 0, length - 1);
    }

    private static boolean isSorted(String[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }
}
