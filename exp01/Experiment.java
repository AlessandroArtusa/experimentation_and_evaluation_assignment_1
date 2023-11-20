package exp01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;


public class Experiment {
    private static List<SortRecord> sortRecords = new ArrayList<>();
    public static void main(String[] args) {
        // Define the sizes of arrays to be sorted
        int length1 = 100;//Change to (10, 100, 1000, 10000)

        // Define the number of iterations for warming up JVM and for actual sorting
        int warmupIterations = 1000;
        int iterations = 30;

        // Perform sorting on different types of arrays
        sortRandomArrayInt(length1, iterations, warmupIterations);
        sortNearlySortedArrayInt(length1, iterations, warmupIterations);
        sortOrderedArrayInt(length1, iterations, warmupIterations);
        sortReverseSortedArrayInt(length1, iterations, warmupIterations);
       
        writeRecordsToCSV("sorting_durations.csv");
    }

    // Method to warm up JVM for a given sorter and array size
    private static void warmUpJVM(Sorter sorter, int length, int iterations) {
        for (int i = 0; i < iterations; i++) {
            Integer[] dummyArray = generateRandomArray(length);
            sorter.sort(dummyArray);
        }
    }

    // Method to generate a random array of a given length
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
        // Create initially sorted array
        for (int i = 0; i < length; i++) {
            array[i] = i;
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

    // Method to sort an ordered array and measure execution time
    private static void sortOrderedArrayInt(int arrayLength, int iterations, int warmupIterations) {
        System.out.println("SORTED ARRAY EXECUTION TIME----------------");
        Integer[] orderedArray = new Integer[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            orderedArray[i] = i;
        }
        // Sorters initialization
        BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<>();
        BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();

        long totalTimePassPerItem = 0;

        // BubbleSortPassPerItem sorting and timing
        warmUpJVM(bubbleSortPassPerItem, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortPassPerItem.sort(orderedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortPassPerItem", arrayLength, endTime - startTime, "Sorted"));
            System.out.println("Time taken to sort with BubbleSortPassPerItem: " + duration + " nanoseconds");
            totalTimePassPerItem += duration;
        }

        // BubbleSortUntilNoChange sorting and timing
        long totalTimeUntilNoChange = 0;
        warmUpJVM(bubbleSortUntilNoChange, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortUntilNoChange.sort(orderedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortUntilNoChange", arrayLength, endTime - startTime, "Sorted"));
            System.out.println("Time taken to sort with BubbleSortUntilNoChange: " + duration + " nanoseconds");
            totalTimeUntilNoChange += duration;
        }

        // BubbleSortWhileNeeded sorting and timing
        long totalTimeWhileNeeded = 0;
        warmUpJVM(bubbleSortWhileNeeded, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortWhileNeeded.sort(orderedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortWhileNeeded", arrayLength, endTime - startTime, "Sorted"));
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

    // Method to sort a random array and measure execution time
    private static void sortRandomArrayInt(int arrayLength, int iterations, int warmupIterations) {
        System.out.println("RANDOM ARRAY EXECUTION TIME----------------");
        Integer[] randomArray = generateRandomArray(arrayLength);

        // Sorters initialization
        BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<>();
        BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();

        // BubbleSortWhileNeeded sorting and timing
        long totalTimeWhileNeeded = 0;
        warmUpJVM(bubbleSortWhileNeeded, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortWhileNeeded.sort(randomArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortWhileNeeded", arrayLength, endTime - startTime, "Random"));
            System.out.println("Time taken to sort with BubbleSortWhileNeeded: " + duration + " nanoseconds");
            totalTimeWhileNeeded += duration;
        }
        // BubbleSortPassPerItem sorting and timing
        long totalTimePassPerItem = 0;
        warmUpJVM(bubbleSortPassPerItem, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortPassPerItem.sort(randomArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortPassPerItem", arrayLength, endTime - startTime, "Random"));
            System.out.println("Time taken to sort with BubbleSortPassPerItem: " + duration + " nanoseconds");
            totalTimePassPerItem += duration;
        }

        // BubbleSortUntilNoChange sorting and timing
        long totalTimeUntilNoChange = 0;
        warmUpJVM(bubbleSortUntilNoChange, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortUntilNoChange.sort(randomArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortUntilNoChange", arrayLength, endTime - startTime, "Random"));
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
        
        // Sorters inizialization
        BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<>();
        BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();
        
        // BubbleSortPassPerItem sorting and timing
        long totalTimeWhileNeeded = 0;
        warmUpJVM(bubbleSortWhileNeeded, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortWhileNeeded.sort(reverseSortedArray.clone());
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortWhileNeeded", arrayLength, endTime - startTime, "Reverse"));
            System.out.println("Time taken to sort with BubbleSortWhileNeeded: " + duration + " nanoseconds");
            totalTimeWhileNeeded += duration;
        }

        // BubbleSortPassPerItem sorting and timing
        long totalTimePassPerItem = 0;
        warmUpJVM(bubbleSortPassPerItem, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortPassPerItem.sort(reverseSortedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortPassPerItem", arrayLength, endTime - startTime, "Reverse"));
            System.out.println("Time taken to sort with BubbleSortPassPerItem: " + duration + " nanoseconds");
            totalTimePassPerItem += duration;
        }

        // BubbleSortUntilNoChange sorting and timing
        long totalTimeUntilNoChange = 0;
        warmUpJVM(bubbleSortUntilNoChange, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortUntilNoChange.sort(reverseSortedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortUntilNoChange", arrayLength, endTime - startTime, "Reverse"));
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

        // Sorters initialization
        BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<>();
        BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();

        // BubbleSortPassPerItem sorting and timing
        long totalTimeWhileNeeded = 0;
        warmUpJVM(bubbleSortWhileNeeded, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortWhileNeeded.sort(nearlySortedArray.clone());
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortWhileNeeded", arrayLength, endTime - startTime, "Nearly sorted"));
            System.out.println("Time taken to sort with BubbleSortWhileNeeded: " + duration + " nanoseconds");
            totalTimeWhileNeeded += duration;
        }
        // BubbleSortPassPerItem sorting and timing
        long totalTimePassPerItem = 0;
        warmUpJVM(bubbleSortPassPerItem, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortPassPerItem.sort(nearlySortedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortPassPerItem", arrayLength, endTime - startTime, "Nearly sorted"));
            System.out.println("Time taken to sort with BubbleSortPassPerItem: " + duration + " nanoseconds");
            totalTimePassPerItem += duration;
        }

        // BubbleSortUntilNoChange sorting and timing
        long totalTimeUntilNoChange = 0;
        warmUpJVM(bubbleSortUntilNoChange, arrayLength, warmupIterations);
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            bubbleSortUntilNoChange.sort(nearlySortedArray);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sortRecords.add(new SortRecord("BubbleSortUntilNoChange", arrayLength, endTime - startTime, "Nearly sorted"));
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
    // Write records to CSV
    private static void writeRecordsToCSV(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write("Sorter,ArraySize,Duration,Array order\n");
            for (SortRecord record : sortRecords) {
                fileWriter.write(record.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
