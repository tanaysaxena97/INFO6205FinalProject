package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.FileUtil;
import edu.neu.coe.info6205.util.SortUtils;

import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

public class DualPivotQuickSort {

    private static List<String> words;
    private static int length;
    private static ComparatorDPQS comparatorDPQS;

    public static void main(String[] args) {
        FileUtil fu = new FileUtil();
        List<String> a = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "demo.txt").toString());
        sort(a, "English");
        fu.writeFile(a, Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "result_file.txt").toString());
    }

    public static void sort(List<String> stringList, String lang) {
        if (stringList == null || stringList.size() == 0) {
            return;
        }
        words = stringList;
        length = stringList.size();
        if(lang.compareTo("Chinese")==0) {
            comparatorDPQS = new ComparatorDPQS(Locale.CHINA);
        } else if(lang.compareTo("English")==0){
            comparatorDPQS = new ComparatorDPQS(Locale.ENGLISH);
        }
        sort(0, length - 1);
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
        if (comparatorDPQS.compare(words.get(low), words.get(high)) > 0)
            swap( low, high);

        int j = low + 1, g = high - 1, k = low + 1;
        String p = words.get(low), q = words.get(high);

        while (k <= g) {

            if (comparatorDPQS.compare(words.get(k), p)<0) {
                swap( k, j);
                j++;
            }

            else if (comparatorDPQS.compare(words.get(k), q)>=0) {
                while (comparatorDPQS.compare(words.get(g), q)>0 && k < g)
                    g--;

                swap( k, g);
                g--;

                if (comparatorDPQS.compare(words.get(k), p)<0) {
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

    private static boolean less(String v, String w) {
        return v.compareToIgnoreCase(w) < 0;
    }

    private static void swap(int i, int j) {
        String swap = words.get(i);
        words.set(i, words.get(j));
        words.set(j, swap);
    }

    private static boolean isSorted() {
        return isSorted(words, 0, length - 1);
    }

    private static boolean isSorted(List<String> a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a.get(i), a.get(i - 1))) return false;
        return true;
    }
}
