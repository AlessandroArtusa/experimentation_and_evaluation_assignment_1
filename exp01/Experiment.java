package exp01;

import java.util.Random;

// import exp01.BubbleSortPassPerItem;
// import exp01.BubbleSortUntilNoChange;
// import exp01.BubbleSortWhileNeeded;

public class Experiment {
    public static void main(String[] args) {
        int length1 = 10;
        int length2 = 100;
        int length3 = 1000;
        int length4 = 10000;
        Integer[] RNumbers = generateRandomArray(length1);

        BubbleSortPassPerItem<Integer> sorter = new BubbleSortPassPerItem<>();

        long startTime;
        long endTime;

        // warmUpJVM(sorter, length1, 1000);
        // System.err.println("Warmup done!");
        // for (int i = 0; i < 30; i++) {
        //     startTime = System.nanoTime();
        //     sorter.sort(RNumbers);
        //     endTime = System.nanoTime();
        //     long duration = endTime - startTime;
        //     System.out.println("Time taken to sort: " + duration + " nanoseconds");

        // }

        sortOrderedArrayInt(length3, length3);

    }

    private static void warmUpJVM(Sorter sorter, int length, int iterations) {
        for (int i = 0; i < iterations; i++) {
            Integer[] dummyArray = generateRandomArray(length);
            sorter.sort(dummyArray);
        }
    }

    private static Integer[] generateRandomArray(int length) {
        Random random = new Random();
        Integer[] array = new Integer[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000); // Generates a random number between 0 and 999
        }

        return array;
    }

    private static void sortOrderedArrayInt(int arrayLength, int iterations) {
        Integer[] orderedArray = new Integer[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            orderedArray[i] = i;
        }

        BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<>();
        BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();

        long totalTimePassPerItem = 0;

        // BubbleSortPassPerItem
        warmUpJVM(bubbleSortPassPerItem, arrayLength, iterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortPassPerItem.sort(orderedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortPassPerItem: " + duration + " nanoseconds");
            totalTimePassPerItem += duration;
        }


        // BubbleSortUntilNoChange
        long totalTimeUntilNoChange = 0;
        warmUpJVM(bubbleSortUntilNoChange, arrayLength, iterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortUntilNoChange.sort(orderedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortUntilNoChange: " + duration + " nanoseconds");
            totalTimeUntilNoChange += duration;
        }

        // BubbleSortWhileNeeded
        long totalTimeWhileNeeded = 0;
        warmUpJVM(bubbleSortWhileNeeded, arrayLength, iterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortWhileNeeded.sort(orderedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortWhileNeeded: " + duration + " nanoseconds");
            totalTimeWhileNeeded += duration;
        }
        
        System.out.println("Average time taken to sort with BubbleSortPassPerItem: " + totalTimePassPerItem / iterations + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortUntilNoChange: " + totalTimeUntilNoChange / iterations + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortWhileNeeded: " + totalTimeWhileNeeded / iterations + " nanoseconds");
    }
}
