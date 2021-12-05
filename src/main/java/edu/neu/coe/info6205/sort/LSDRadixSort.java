package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.FileUtil;
import edu.neu.coe.info6205.util.SortUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// n: input size, d: number of distinct elements, complexity - O(n + d*lg(d))
public class LSDRadixSort {
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
        Integer maxStringLength = sortUtils.getMaxStringLength(xs);
        for (int i = 0; i < xs.size(); i++) aux.add("");
        xs = sortUtils.padMSDStrings(xs);
        for (int d = maxStringLength - 1; d >= 0; d--) {
            sort(xs, d);
        }
        return sortUtils.removeMSDPadding(xs);
    }

    private void sort(List<String> xs, int d) {
        for (int i = 0; i < xs.size(); i++) aux.set(i, "");
        // counting step
        Map<Character, Integer> count = sortUtils.getFrequencyTreeMap(xs, 0, xs.size() - 1, d);
        // cumulative sum step  ---  for i in range(1, n): a[i] += a[i-1]
        count = sortUtils.cumulativeSum(count);
        // populate the aux array from l to r
        for (int i = xs.size() - 1; i >= 0; i--) {
            Character c = xs.get(i).charAt(d);
            int newIdx = count.get(c) - 1;
            aux.set(newIdx, xs.get(i));
            count.put(c, count.get(c) - 1);
        }
        // copy aux to xs
        for (int i = 0; i < xs.size(); i++) {
            xs.set(i, aux.get(i));
        }
    }

    public static void main(String []args) {
        FileUtil fu = new FileUtil();
        List<String> a = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString());
        a = (new LSDRadixSort()).sort(a, "Chinese");
        List sample = a.subList(0, 99);
        fu.writeFile(sample, Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "sortingSample", "LSDSample.txt").toString());
    }
}