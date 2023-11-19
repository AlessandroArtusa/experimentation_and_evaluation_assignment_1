# NOTES

## Hypotheses
-NULL: There is no significant difference in the average execution time between the "BubbleSortPassPerItem", "BubbleSortUntilNoChange", and "BubbleSortWhileNeeded" algorithms when sorting nearly sorted arrays.

- NULL: The increase in the size of the dataset does not significantly affect the relative performance differences between the BubbleSortPassPerItem, BubbleSortUntilNoChange, and BubbleSortWhileNeeded algorithms.

- Hypothesis 2: The sorting algorithm with the best execution time is <code>BubbleSortWhileNeeded</code>. The hypothesis was formulated by analyzing the code and the complexity of each sorting algorithm. The <code>BubbleSortPassPerItem</code> algorithm has a complexity of [...]

## Method

## Variables:

 >The suspected cause for observed change, hence of primary interest.Values will be manipulated by the experimenter (e.g., present/absent, intensity).Also known as: Factor of the experiment.
### Independent: 
- sorting algorithms.
   
### Dependent: 
- Execution time of the sorting process.
  
### Control variable: 
- Size of the array(10, 100, 1000, 10000, 100000) 
- type of data(int, float, char, byte) 
- system specifications where the test runs(Ubuntu 22.04, Windows 11, Java version) 
- warmup time.
 > Time taken by the JVM to optimize the code execution before stable performance measurements can be made. Allow each algorithm to run for a set number of iterations before recording the execution time to mitigate JVM optimization effects.


## Design
Type of Study: Experiment

Number of Factors: Single-Factor Design

Explanation: The Design is single-factor because repeated-measures with the only factor a sorting algorithm is used.
The same dataset is sorted using each algorithm to directly compare the results.

 ### Apparatus and Materials
 The experiment was conducted on a computer with the following specifications: (CPU, RAM, JavaVersion, OS) </br>
 System.nanoTime() used for mesuring execution time

  ### Procedure
Each algorithm was executed 50 times on each dataset, and the execution time was recorded. The computer was dedicated solely to this task during the experiment to prevent extraneous processes from affecting the results.



 