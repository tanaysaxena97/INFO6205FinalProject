package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.sort.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.FileUtil;
import edu.neu.coe.info6205.util.OrderMaster;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DualPivotQuickSortTest {

    private final BaseHelper<String> helper = new BaseHelper<>("dummy helper");

    @Test
    public void testSortString1() {
        List<String> input1 = new ArrayList<>(), input2;
        FileUtil fu = new FileUtil();
        input1 = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "demo.txt").toString().replaceAll("%20", " "));
        input2 = new ArrayList<>(input1);
        DualPivotQuickSort.sort(input1, "English");
        Collections.sort(input2);
        for (int i = 0; i < input1.size(); i++) {
            assertEquals(input2.get(i), input1.get(i));
        }
    }

    @Test
    public void sortTest2() {
        FileUtil fu = new FileUtil();
        List<String > xs = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString());
        List<String > target = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "sortedChinese.txt").toString());
        DualPivotQuickSort.sort(xs, "Chinese");
        for (int i = 0; i < xs.size(); i++) {
            assertEquals(target.get(i), xs.get(i));
        }
    }
}
