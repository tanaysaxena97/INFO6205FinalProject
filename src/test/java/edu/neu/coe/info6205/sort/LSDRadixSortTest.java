package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class LSDRadixSortTest {
    @Test
    public void LSDRadixSortTest() {
        LSDRadixSort lsdRadixSort = new LSDRadixSort();
        FileUtil fu = new FileUtil();
        List<String> xs = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString());
        List<String> target = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "sortedChinese.txt").toString());
        xs = lsdRadixSort.sort(xs, "Chinese");
        for (int i = 0; i < xs.size(); i++) {
            assertEquals(target.get(i), xs.get(i));
        }
    }

    @Test
    public void LSDRadixSortTest2() {
        LSDRadixSort lsdRadixSort = new LSDRadixSort();
        FileUtil fu = new FileUtil();
        List<String> xs = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "englishStrings.txt").toString());
        List<String> target = fu.readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "englishStrings.txt").toString());
        target = target.stream().sorted().collect(Collectors.toList());
        xs = lsdRadixSort.sort(xs);
        for (int i = 0; i < xs.size(); i++) {
            assertEquals(target.get(i), xs.get(i));
        }
    }
}
