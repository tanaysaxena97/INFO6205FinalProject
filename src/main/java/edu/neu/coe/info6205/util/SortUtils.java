package edu.neu.coe.info6205.util;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SortUtils {
    private final Character paddingChar = ' ';
    private String lang;
    private OrderMaster om;


    public SortUtils() {
        lang = "English";
        om = new OrderMaster();
    }
    public void setChinese() {
        lang = "Chinese";
    }
    public Integer getMaxStringLength(List<String> xs) {
        if (xs == null || xs.isEmpty()) return -1;
        int maxStringLength = xs.get(0).length();
        for (String x : xs) {
            maxStringLength = Math.max(maxStringLength, x.length());
        }
        return maxStringLength;
    }

    public List<String> padMSDStrings(List<String> xs) {
        final Integer maxStringLength = getMaxStringLength(xs);
        xs = xs.stream().map(currStr -> currStr +
                String.valueOf(paddingChar).repeat(Math.max(0, maxStringLength - currStr.length())))
                                            .collect(Collectors.toList());
        return xs;
    }

    public List<String> removeMSDPadding(List<String> xs) {
        return xs.stream().map(String::trim).collect(Collectors.toList());
    }

    public Map<Character, Integer> getFrequencyTreeMap(List<String> xs, int l, int r, int d) {
        if (l > r) return null;
        Map<Character, Integer> count;
        if (lang.equals("Chinese")) {
            count = new TreeMap<>((a, b) -> om.compare(a, b));
        }
        else {
            count = new TreeMap<>();
        }
        for (int i = l; i <= r; i++) {
            Character c = xs.get(i).charAt(d);
            if (count.containsKey(c)) count.put(c, count.get(c) + 1);
            else count.put(c, 1);
        }
        return count;
    }

    public Map<Character, Integer> cumulativeSum(Map<Character, Integer> count) {
        Integer sm = 0;
        for (Map.Entry<Character, Integer> entry: count.entrySet()) {
            entry.setValue(sm + entry.getValue());
            sm = entry.getValue();
        }
        return count;
    }
}
