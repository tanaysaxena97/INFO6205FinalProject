package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.sql.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class Main {

    public static void main(String[] args) {

        int sizeOfArr = 100;

        ArrayList<String> outputPrints = new ArrayList<>();

        for (int i = 0; i < 10; i++) {


            UnaryOperator<Integer[]> benchmarkPre = param -> {
                return param.clone();
            };

            Consumer<Integer[]> benchmarkFunc = param -> {
                (new InsertionSort<Integer>()).sort(param, 0, param.length);
            };

            Benchmark_Timer<Integer[]> newBenchTimer = new Benchmark_Timer<Integer[]>(
                    "Main Functinon Benchmark",
                    benchmarkPre,
                    benchmarkFunc,
                    null
            );


            final Integer[] originalList = generateRandomArray(100);

            Integer[] testArr1 = originalList.clone(); // random elements

            Integer[] testArr2 = originalList.clone(); // sorted elements

            (new InsertionSort<Integer>()).sort((Integer[]) testArr2, 0, testArr2.length);


            Integer[] testArr3 = originalList.clone(); // Partially Sorted

            (new InsertionSort<Integer>()).sort((Integer[]) testArr3, 0, testArr3.length/2);


            Integer[] testArr4 = originalList.clone(); // reverse Sorted

            (new InsertionSort<Integer>()).sort((Integer[]) testArr4, 0, testArr4.length);

            Collections.reverse(Arrays.asList(testArr4));


            double timeElapsedRandom = newBenchTimer.run(testArr1, 10);

            double timeElapsedSorted = newBenchTimer.run(testArr2, 10);

            double timeElapsedPartiallySorted = newBenchTimer.run(testArr3, 10);

            double timeElapsedReverseSorted =  newBenchTimer.run(testArr4, 10);

            outputPrints.add( sizeOfArr + ", " + timeElapsedRandom + ", "+ timeElapsedSorted + ", " + timeElapsedPartiallySorted + ", " + timeElapsedReverseSorted);

            sizeOfArr *= 2;
        }


        for (int i = 0; i < 10; i++) {
            
        }
    }

    private static Integer[] generateRandomArray(int n){
        Integer[] list = new Integer[n];

        Random random = new Random();

        for (int i = 0; i < n; i++)
        {
            list[i] = random.nextInt(100);
        }
        return list;
    }



}
