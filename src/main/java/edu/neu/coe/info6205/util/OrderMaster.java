package edu.neu.coe.info6205.util;


import java.nio.file.Paths;
import java.text.Collator;
import java.util.*;

public class OrderMaster {
    private Map<Character, Integer> scoreMap;

    public OrderMaster() {
        scoreMap = new HashMap<>();
        fun((new FileUtil()).readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString()));
    }

    public void fun(List<String> tmp) {
        Set<Character> st = new HashSet<>();
        tmp.stream().forEach(x -> {for(int i = 0; i < x.length(); i++) st.add(x.charAt(i));});
        List<Character> characters = new ArrayList<>();
        for (Character c: st) characters.add(c);
        Collections.sort(characters, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Collator.getInstance(Locale.CHINA).compare(o1.toString(), o2.toString());
            }
        });
        for (int i = 0; i < characters.size(); i++) scoreMap.put(characters.get(i), i + 1);
        scoreMap.put(' ', 0);
    }

    public int compare(Character o1, Character o2) {
        Integer a = scoreMap.get(o1);
        Integer b = scoreMap.get(o2);
        int x = a.compareTo(b);
        return x;
    }

    public static void main(String[] args) {
        List<String> list = (new FileUtil()).readFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "shuffledChinese.txt").toString());
        (new OrderMaster()).fun(list);
    }
}
