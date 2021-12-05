package edu.neu.coe.info6205;

import edu.neu.coe.info6205.sort.DualPivotQuickSort;
import edu.neu.coe.info6205.sort.LSDRadixSort;
import edu.neu.coe.info6205.sort.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.FileUtil;

import java.nio.file.Paths;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SortingBenchmark {
    private static MSDRadixSort msdRadixSort = new MSDRadixSort();
    private static LSDRadixSort lsdRadixSort = new LSDRadixSort();
    private static PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.chineseEncoder, false, false);
    private static DualPivotQuickSort dpqs = new DualPivotQuickSort();

    private static List<String> getStringList(List<String> source, int size) {
        return new ArrayList<>(source.subList(0, size - 1));
    }

    private static List<String> expandNFold(List<String> list, int n) {
        int m = list.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                list.add(list.get(j));
            }
        }
        return list;
    }

    public static void trials() {
        List<String> csvOutput = new ArrayList<>();
        FileUtil fu = new FileUtil();
        List<String> sourceStrings = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString());
        sourceStrings = expandNFold(sourceStrings, 8);
        List<String> sysInput = getStringList(sourceStrings, 500000);
        long startTime = System.currentTimeMillis();
        Collections.sort(sysInput, Collator.getInstance(Locale.CHINA));
        long endTime = System.currentTimeMillis();
        csvOutput.add("inputSize, LSD, MSD, husky, DPQS");
        System.out.println("system warmup: " + Long.toString(endTime - startTime));
        for (int size = 500000; size <= 8000000; size *= 2) {
            StringBuilder row = new StringBuilder();
            row.append(size + ",");
            List<String> msdInput = getStringList(sourceStrings, size);
            List<String> lsdInput = getStringList(sourceStrings, size);
            List<String> huskyInput = getStringList(sourceStrings, size);
            List<String> dpqsInput = getStringList(sourceStrings, size);

            startTime = System.currentTimeMillis();
            lsdRadixSort.sort(lsdInput, "Chinese");
            endTime = System.currentTimeMillis();
            System.out.println("LSD: " + (endTime - startTime));
            row.append((endTime - startTime) + ",");

            startTime = System.currentTimeMillis();
            msdRadixSort.sort(msdInput, "Chinese");
            endTime = System.currentTimeMillis();
            System.out.println("MSD: " + (endTime - startTime));
            row.append((endTime - startTime) + ",");

            startTime = System.currentTimeMillis();
            sorter.sort(huskyInput.toArray(new String[0]));
            Collections.sort(huskyInput);
            endTime = System.currentTimeMillis();
            System.out.println("Husky: " + (endTime - startTime));
            row.append((endTime - startTime) + ",");

            startTime = System.currentTimeMillis();
            dpqs.sort(dpqsInput, "Chinese");
            endTime = System.currentTimeMillis();
            System.out.println("DPQS: " + (endTime - startTime));
            row.append((endTime - startTime) + ",");

            csvOutput.add(row.toString());
        }
        fu.writeFile(csvOutput, Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "BenchmarkOutput.txt").toString());
    }

    public void testHuskySort(List<String> sourceString) {
        long startTime = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();
        System.out.println("MSD: " + (endTime - startTime));
    }

    public static void main(String[] args) {
        trials();
    }
}
