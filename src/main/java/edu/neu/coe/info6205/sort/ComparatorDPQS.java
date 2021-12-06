package edu.neu.coe.info6205.sort;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class ComparatorDPQS implements Comparator<String> {

    private Collator collator;
    public ComparatorDPQS(Locale locale) {
        this.collator = Collator.getInstance(locale);
    }

    @Override
    public int compare(String o1, String o2) {
        return collator.compare(o1, o2);
    }
}
