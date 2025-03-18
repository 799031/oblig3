package uke11oppave;

import java.util.*;

public class finneTall {
    public static void main(String[] args) {
    	int elementer = 1_000_000;
        final int hopp = 45713;
        
        HashSet<Integer> hashSet = new HashSet<>();
        Integer[] array = new Integer[elementer];
        
        int value = 376;
        for (int i = 0; i < elementer; i++) {
            hashSet.add(value);
            array[i] = value;
            value = (value + hopp) % elementer;
        }
        
        Arrays.sort(array);
        
        Random rand = new Random();
        int[] find = new int[elementer];
        for (int i = 0; i < elementer; i++) {
        	find[i] = rand.nextInt(elementer);
        }
        
        long startTid = System.nanoTime();
        int hashCount = 0;
        for (int num : find) {
            if (hashSet.contains(num)) {
            	hashCount++;
            }
        }
        long sluttTid = System.nanoTime();
        long hashSetTid = sluttTid - startTid;
        
        startTid = System.nanoTime();
        int arrCount = 0;
        for (int num : find) {
            if (Arrays.binarySearch(array, num) >= 0) {
            	arrCount++;
            }
        }
        sluttTid = System.nanoTime();
        long arrayTid = sluttTid - startTid;
        
        System.out.println("HashSet antall: " + hashCount);
        System.out.println("Tid : " + hashSetTid / 1_000_000 + " ms\n");
        System.out.println("Array antall: " + arrCount);
        System.out.println("Tid : " + arrayTid / 1_000_000 + " ms");
    }
}
