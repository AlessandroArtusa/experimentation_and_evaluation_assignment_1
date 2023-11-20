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
        
        warmUpJVM(sorter, length1, 1000);
        System.err.println("Warmup done!");
        for(int i = 0; i<30; i++){
            startTime = System.nanoTime();
            sorter.sort(RNumbers);
            endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Time taken to sort: " + duration + " nanoseconds");

        }

    
    }

    private static void warmUpJVM(BubbleSortPassPerItem<Integer> sorter, int length, int iterations) {
        for (int i = 0; i < iterations; i++) {
            Integer[] dummyArray = generateRandomArray(length);
            System.out.println("Array sorted "+ i);
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

    
}
