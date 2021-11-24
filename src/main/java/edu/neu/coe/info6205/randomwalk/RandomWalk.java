/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.io.Console;
import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        // IMPLEMENTED
        /*
         * Possible Directions:
         * North (0, 1), South (0, -1), West (-1, 0), East (1, 0)
         */
//        System.out.println(String.format("-> Making a random move to: (%d, %d)", dx, dy));
        this.x += dx;
        this.y += dy;
//        System.out.println(String.format("-> Position after making the random move: (%d, %d)", this.x, this.y));
    }


    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // IMPLEMENTED
        for (int i = 0; i < m; i++) {
            randomMove(); // make a random move
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        // IMPLEMENTED

        /*Initial and final positions where the drunkard starts...*/
        int initialPosX = 0, initialPosY = 0;
        int finalPosX = this.x, finalPosY = this.y;

        /*
         * Calculating the distance using Euclidean Distance formula
         * distance (d) = sqrt of ( square (y2 - y1) + square (x2 - x1) )
         *
         * Where:
         * x1 is the initial point on X-axis
         * x2 is the final point on X-axis
         * y1 is the initial point on Y-axis
         * y2 is the initial point on Y-axis
         */

        /*Calculating the absolute location on X-axis and Y-axis*/
        int yAxisDifference = Math.abs(finalPosY - initialPosY);
        int xAxisDifference = Math.abs(finalPosX - initialPosX);

        /*Squaring the difference*/
        double yAxisDifferenceSquared = Math.pow(yAxisDifference, 2);
        double xAxisDifferenceSquared = Math.pow(xAxisDifference, 2);

        /*Finally, calculating the distance*/
        double distance = Math.sqrt(yAxisDifferenceSquared + xAxisDifferenceSquared);


        System.out.println(String.format("-> Distance from the initial position i.e the lamp post at (0, 0)," +
                "where the final position is after n steps is (%d, %d), is %.1f\n---------------------",
                this.x, this.y, distance));
        return distance;
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int m = Integer.parseInt(args[0]);
        int n = 30;
        if (args.length > 1) n = Integer.parseInt(args[1]);
        double meanDistance = randomWalkMulti(m, n);
        System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
    }

}
