package edu.neu.coe.info6205;

import edu.neu.coe.info6205.sort.LSDRadixSort;
import edu.neu.coe.info6205.sort.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.sort.huskySortUtils.SequenceEncoder_Collator;
import edu.neu.coe.info6205.util.FileUtil;
//import edu.neu.coe.info6205.sort.huskySort.

import java.nio.file.Paths;
import java.sql.Time;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingBenchmark {
    private static MSDRadixSort msdRadixSort = new MSDRadixSort();
    private static LSDRadixSort lsdRadixSort = new LSDRadixSort();
    private static PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.chineseEncoder, false, false);

    private static List<String> getStringList(List<String> source, int size) {
        return new ArrayList<>(source.subList(0, size - 1));
    }

    public static void trials() {
        FileUtil fu = new FileUtil();
        List<String> sourceStrings = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString());
        for (int size = 500000; size <= 999998; size *= 2) {
            List<String> msdInput = getStringList(sourceStrings, size);
            List<String> lsdInput = getStringList(sourceStrings, size);
            List<String> huskyInput = getStringList(sourceStrings, size);
            long startTime = System.currentTimeMillis();
            lsdRadixSort.sort(lsdInput);
            long endTime = System.currentTimeMillis();
            System.out.println("LSD: " + Long.toString(endTime - startTime));
            startTime = System.currentTimeMillis();
            msdRadixSort.sort(msdInput, "Chinese");
            endTime = System.currentTimeMillis();
            System.out.println("MSD: " + Long.toString(endTime - startTime));
            startTime = System.currentTimeMillis();
            sorter.sort(huskyInput.toArray(new String[0]));
            Collections.sort(huskyInput);
            endTime = System.currentTimeMillis();
            System.out.println("husky: " + Long.toString(endTime - startTime));


        }
    }

    public void testHuskySort(List<String> sourceString) {
        long startTime = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();
        System.out.println("MSD: " + Long.toString(endTime - startTime));
    }

    public static void main(String[] args) {
        trials();
    }
}
