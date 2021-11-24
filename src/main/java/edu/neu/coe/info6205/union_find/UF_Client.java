package edu.neu.coe.info6205.union_find;
import java.util.Scanner;
import java.util.Random;

public final class UF_Client {

    public static int count(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n);
        int count = 0;
        while (uf.components() != 1) {
            int p = new Random().nextInt(n);
            int q = new Random().nextInt(n);
            if (!uf.isConnected(p, q)) {
                uf.union(p, q);
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter different values of n, and -1 to end the program.");
//        int n = scanner.nextInt();
//        while(n != -1) {
//            int numberOfConnections = UF_Client.count(n);
//            System.out.println("Total " + numberOfConnections + " connections, for n = " + n);
//            n = scanner.nextInt();
//        }
//        System.out.println("Program Terminated...");
        int[] nValues = new int[] {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600, 51200};

        for (int i = 0; i < nValues.length; i++) {
            int numberOfConnections = UF_Client.count(nValues[i]);
            System.out.println("Total connections, for n = " + nValues[i] + "  \t are " + numberOfConnections);
        }

    }
}
