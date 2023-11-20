package exp01;

import java.util.Random;

public class Experiment {
    public static void main(String[] args) {
        int length1 = 10;
        int length2 = 100;
        int length3 = 1000;
        int length4 = 10000;
        int warmupIterations = 1000;
        int iterations = 30;
        sortRandomArrayInt(length1, iterations, warmupIterations);
        sortNearlySortedArrayInt(length1, iterations, warmupIterations);
        sortOrderedArrayInt(length1, iterations, warmupIterations);
        sortReverseSortedArrayInt(length1, iterations, warmupIterations);

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

    // Method to generate a nearly sorted array
    private static Integer[] generateNearlySortedArray(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = i; // Initially sorted
        }
        Random random = new Random();
        // Swap a few elements to make it nearly sorted
        for (int i = 0; i < length / 10; i++) {
            int index1 = random.nextInt(length);
            int index2 = random.nextInt(length);
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
        return array;
    }

    // Method to generate a reverse sorted array
    private static Integer[] generateReverseSortedArray(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = length - i - 1; // Reverse order
        }
        return array;
    }

    private static void sortOrderedArrayInt(int arrayLength, int iterations, int warmupIterations) {
        System.out.println("SORTED ARRAY EXECUTION TIME----------------");
        Integer[] orderedArray = new Integer[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            orderedArray[i] = i;
        }

        BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<>();
        BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();

        long totalTimePassPerItem = 0;

        // BubbleSortPassPerItem
        warmUpJVM(bubbleSortPassPerItem, arrayLength, warmupIterations);
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
        warmUpJVM(bubbleSortUntilNoChange, arrayLength, warmupIterations);
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
        warmUpJVM(bubbleSortWhileNeeded, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortWhileNeeded.sort(orderedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortWhileNeeded: " + duration + " nanoseconds");
            totalTimeWhileNeeded += duration;
        }

        System.out.println("Average time taken to sort with BubbleSortPassPerItem: " + totalTimePassPerItem / iterations
                + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortUntilNoChange: "
                + totalTimeUntilNoChange / iterations + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortWhileNeeded: " + totalTimeWhileNeeded / iterations
                + " nanoseconds");
    }

    private static void sortRandomArrayInt(int arrayLength, int iterations, int warmupIterations) {
        System.out.println("RANDOM ARRAY EXECUTION TIME----------------");
        Integer[] randomArray = generateRandomArray(arrayLength);

        BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<>();
        BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();

        // BubbleSortWhileNeeded
        long totalTimeWhileNeeded = 0;
        warmUpJVM(bubbleSortWhileNeeded, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortWhileNeeded.sort(randomArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortWhileNeeded: " + duration + " nanoseconds");
            totalTimeWhileNeeded += duration;
        }
        // BubbleSortPassPerItem
        long totalTimePassPerItem = 0;
        warmUpJVM(bubbleSortPassPerItem, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortPassPerItem.sort(randomArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortPassPerItem: " + duration + " nanoseconds");
            totalTimePassPerItem += duration;
        }

        // BubbleSortUntilNoChange
        long totalTimeUntilNoChange = 0;
        warmUpJVM(bubbleSortUntilNoChange, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortUntilNoChange.sort(randomArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortUntilNoChange: " + duration + " nanoseconds");
            totalTimeUntilNoChange += duration;
        }
        System.out.println("Average time taken to sort with BubbleSortPassPerItem: " + totalTimePassPerItem / iterations
                + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortUntilNoChange: "
                + totalTimeUntilNoChange / iterations + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortWhileNeeded: " + totalTimeWhileNeeded / iterations
                + " nanoseconds");

    }

    // Method to sort reverse sorted array
    private static void sortReverseSortedArrayInt(int arrayLength, int iterations, int warmupIterations) {
        System.out.println("REVERSE SORTED ARRAY EXECUTION TIME----------------");
        Integer[] reverseSortedArray = generateReverseSortedArray(arrayLength);

        BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<>();
        BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();

        long totalTimeWhileNeeded = 0;
        warmUpJVM(bubbleSortWhileNeeded, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortWhileNeeded.sort(reverseSortedArray.clone());
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortWhileNeeded: " + duration + " nanoseconds");
            totalTimeWhileNeeded += duration;
        }

        // BubbleSortPassPerItem
        long totalTimePassPerItem = 0;
        warmUpJVM(bubbleSortPassPerItem, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortPassPerItem.sort(reverseSortedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortPassPerItem: " + duration + " nanoseconds");
            totalTimePassPerItem += duration;
        }

        // BubbleSortUntilNoChange
        long totalTimeUntilNoChange = 0;
        warmUpJVM(bubbleSortUntilNoChange, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortUntilNoChange.sort(reverseSortedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortUntilNoChange: " + duration + " nanoseconds");
            totalTimeUntilNoChange += duration;
        }
        System.out.println("Average time taken to sort with BubbleSortPassPerItem: " + totalTimePassPerItem / iterations
                + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortUntilNoChange: "
                + totalTimeUntilNoChange / iterations + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortWhileNeeded: " + totalTimeWhileNeeded / iterations
                + " nanoseconds");
    }

    // Method to sort nearly sorted array
    private static void sortNearlySortedArrayInt(int arrayLength, int iterations, int warmupIterations) {
        System.out.println("NEARLY SORTED ARRAY EXECUTION TIME----------------");
        Integer[] nearlySortedArray = generateNearlySortedArray(arrayLength);

        BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<>();
        BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();

        long totalTimeWhileNeeded = 0;
        warmUpJVM(bubbleSortWhileNeeded, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortWhileNeeded.sort(nearlySortedArray.clone());
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortWhileNeeded: " + duration + " nanoseconds");
            totalTimeWhileNeeded += duration;
        }
        // BubbleSortPassPerItem
        long totalTimePassPerItem = 0;
        warmUpJVM(bubbleSortPassPerItem, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortPassPerItem.sort(nearlySortedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortPassPerItem: " + duration + " nanoseconds");
            totalTimePassPerItem += duration;
        }

        // BubbleSortUntilNoChange
        long totalTimeUntilNoChange = 0;
        warmUpJVM(bubbleSortUntilNoChange, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortUntilNoChange.sort(nearlySortedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort with BubbleSortUntilNoChange: " + duration + " nanoseconds");
            totalTimeUntilNoChange += duration;
        }
        System.out.println("Average time taken to sort with BubbleSortPassPerItem: " + totalTimePassPerItem / iterations
                + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortUntilNoChange: "
                + totalTimeUntilNoChange / iterations + " nanoseconds");
        System.out.println("Average time taken to sort with BubbleSortWhileNeeded: " + totalTimeWhileNeeded / iterations
                + " nanoseconds");
    }

}
