package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.FileUtil;
import edu.neu.coe.info6205.util.OrderMaster;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;
import java.util.*;

public class MSDRadixSortTest {
    @Test
    public void sortTest1() {
        // test the sorted order
        List<String> input1 = new ArrayList<>(), input2;
        FileUtil fu = new FileUtil();
        input1 = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "englishStrings.txt").toString().replaceAll("%20", " "));
        input2 = new ArrayList<>(input1);
        input1 = (new MSDRadixSort()).sort(input1);
        Collections.sort(input2);
        for (int i = 0; i < input1.size(); i++) {
            assertEquals(input2.get(i), input1.get(i));
        }
    }

    @Test
    public void sortTest2() {
        // test if none of the keys has been altered or is missing
        FileUtil fu = new FileUtil();
        List<String > xs = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString());
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        xs.stream().forEach(x -> s1.add(x));
        xs = (new MSDRadixSort()).sort(xs);
        xs.stream().forEach(x -> s2.add(x));
        assertEquals(s1.size(), s2.size());
        for (String key: s1) {
            assertTrue(s2.contains(key));
        }
    }

    @Test
    public void sortTest3() {
        FileUtil fu = new FileUtil();
        List<String > xs = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString());
        List<String > target = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "sortedChinese.txt").toString());
        xs = (new MSDRadixSort()).sort(xs, "Chinese");
        OrderMaster om = new OrderMaster();
        for (int i = 0; i < xs.size(); i++) {
            assertEquals(target.get(i), xs.get(i));
        }
    }
}
