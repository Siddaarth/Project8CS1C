import java.security.KeyStore;
import java.util.*;

/**
 * Driver class to test out how runtime is impacted from recursion limits and array sizes
 * @author Siddaarth Prasanna
 */
public class QSortRecursionLimitTest {
    // constants to control recursion limit tests
    final static int RECURSION_LIMIT_START = 2;
    final static int RECURSION_LIMIT_INCREMENT = 2;
    final static int RECURSION_LIMIT_MAX = 300;

    // constants to control size of test arrays
    final static int ARRAY_SIZE_START = 20000;
    final static int ARRAY_SIZE_MAX = 10020000;
    final static int ARRAY_SIZE_INCREMENT = 500000;

    // constant to convert nanotime to milliseconds time
    final static int NANO_TO_MILLI = 1000000;


    /**
     * method to return randomly generated array of given size
     * @param size
     * @return array of specified size with randomly generated numbers
     */
    public static Integer[] fillArrayRandom(int size) {
        Integer[] randomArray = new Integer[size];

        for (int i = 0; i < size; i++) {
            randomArray[i] = (int)(Math.random() * Integer.MAX_VALUE);
        }

        return randomArray;
    }

    /**
     * Main driver code, loops through for different array sizes and prints out runtime for different recursion limits
     * @param args
     */
    public static void main(String[] args) {
        // variables to keep track of start and stop time for each sort
        long startTime, stopTime;

        // keep track of recursion limits
        ArrayList<Integer> recursionLimits = new ArrayList<>();

        // populate arraylist
        for (int recursionLimit = RECURSION_LIMIT_START; recursionLimit <= RECURSION_LIMIT_MAX; recursionLimit = recursionLimit + RECURSION_LIMIT_INCREMENT) {
            recursionLimits.add(recursionLimit);
        }

        // keep track of array sizes
        ArrayList<Integer> arraySizes = new ArrayList<>();

        // populate array sizes
        for (int size = ARRAY_SIZE_START; size <= ARRAY_SIZE_MAX; size += ARRAY_SIZE_INCREMENT) {
            arraySizes.add(size);
        }

        // keep track of the data
        ArrayList<ArrayList<Double>> data = new ArrayList<>();


        // loop through different sizes for arrays
        for (int size :arraySizes) {
            Integer[] randomArray = fillArrayRandom(size);

            ArrayList<Double> tempData = new ArrayList<>();

            // loop through for different recursion limits
            for (int recursionLimit: recursionLimits) {
                // set recursion limit
                FHsort.setRecursionLimit(recursionLimit);

                double timeInMS = 0;

                // perform test 3x
                for (int i = 0; i < 3; i++) {
                    // copy array to sort
                    Integer[] tempArray = randomArray.clone();

                    // measure start and stop time around the mergeSort function
                    startTime = System.nanoTime();
                    FHsort.quickSort(tempArray);
                    stopTime = System.nanoTime();

                    // convert to ms, truncate to int
                    timeInMS += ((stopTime * 1.0 - startTime) / NANO_TO_MILLI);
                }

                tempData.add(timeInMS/3);

            }

            data.add(tempData);
            System.out.println(tempData);

        }
        // print out all the data

        System.out.print("\t");
        // first print all the column labels
        for (int size :arraySizes) {
            System.out.print(size + "\t");
        }
        System.out.println();

        // print out recursion limit, then the times for their sizes
        for (int recursionLimitInd = 0; recursionLimitInd < recursionLimits.size(); recursionLimitInd++) {
            System.out.print(recursionLimits.get(recursionLimitInd) + "\t");

            for (int sizeInd = 0; sizeInd < arraySizes.size(); sizeInd++) {
                System.out.print(data.get(sizeInd).get(recursionLimitInd) + "\t");
            }

            System.out.print("\n");
        }
    }
}
