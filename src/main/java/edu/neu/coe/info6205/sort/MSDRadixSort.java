package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.FileUtil;
import edu.neu.coe.info6205.util.SortUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
// n: input size, d: number of distinct elements, complexity - O(n + d*lg(d))
public class MSDRadixSort {
    private Integer maxStringLength;
    private List<String> aux;
    private SortUtils sortUtils;

    public List<String> sort(List<String> xs) {
        return sort(xs, "English");
    }

    public List<String> sort(List<String> xs, String lang) {
        if (xs.size() == 0) return xs;
        aux = new ArrayList<>();
        sortUtils = new SortUtils();
        if (lang.equals("Chinese")) sortUtils.setChinese();
        maxStringLength = sortUtils.getMaxStringLength(xs);
        for (int i = 0; i < xs.size(); i++) aux.add("");
        xs = sortUtils.padMSDStrings(xs);
        sort(xs, 0, xs.size() - 1, 0);
        return sortUtils.removeMSDPadding(xs);
    }

    private void sort(List<String> xs, int l, int r, int d) {
        if (d >= maxStringLength) return;
        // counting step
        Map<Character, Integer> count = sortUtils.getFrequencyTreeMap(xs, l, r, d);

        // TODO: additionally we can use an LinkedHashMap to store the sorted and counted frequencies from the map, and iterate over the in the future steps. (maybe)
        // TODO: use msd radix sort to sort buckets of strings having same lengths, and then use k-way merge of sorted lists https://leetcode.com/problems/merge-k-sorted-lists/
        // populate indices array for iteration
        List<Integer> indices = new ArrayList<>(count.values());

        // cumulative sum step  ---  for i in range(1, n): a[i] += a[i-1]
        count = sortUtils.cumulativeSum(count);

        // populate the aux array from l to r
        for (int i = l; i <= r; i++) {
            Character c = xs.get(i).charAt(d);
            int newIdx = l + count.get(c) - 1;
            aux.set(newIdx, xs.get(i));
            count.put(c, count.get(c) - 1);
        }

        // copy aux to xs
        for (int i = l; i <= r; i++) {
            xs.set(i, aux.get(i));
        }

        // recursive step
        int lo = l;
        for (Integer index : indices) {
            this.sort(xs, lo, lo + index - 1, d + 1);
            lo = lo + index;
        }
    }

    public static void main(String []args) {
        FileUtil fu = new FileUtil();
        List<String> a = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString());
        a = (new MSDRadixSort()).sort(a, "Chinese");
        List sample = a.subList(0, 99);
        fu.writeFile(sample, Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "sortingSample", "MSDSample.txt").toString());
    }
}