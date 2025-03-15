package leetcode.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MaximumPopulation {

    public static void main(String[] args) {
//        int[][] logs = {{2008,2026},{2004,2008},{2034,2035},{1999,2050},{2049,2050},{2011,2035},{1966,2033},{2044,2049}};
        int[][] logs = {{1993,1999},{2000,2010}};
        System.out.println(maximumPopulation(logs));
    }

    public static int maximumPopulation(int[][] logs) {
        int[] years = new int[2051];

        for (int[] log : logs) {
            years[log[0]]++;
            years[log[1]]--;
        }

        int maxPopulation = 0;
        int earliestYear = 0;
        int currentPopulation = 0;
        for (int year = 1950; year < years.length; year++) {
            currentPopulation += years[year];
            if (currentPopulation > maxPopulation) {
                maxPopulation = currentPopulation;
                earliestYear = year;
            }
        }

        return earliestYear;
    }
}
