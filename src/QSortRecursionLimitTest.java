import java.security.KeyStore;
import java.util.*;

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
            randomArray[i] = (int)(Math.random() * size);
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

        StringBuilder raw = new StringBuilder();

        // loop through different sizes for arrays
        for (int recursionLimit = RECURSION_LIMIT_START; recursionLimit <= RECURSION_LIMIT_MAX; recursionLimit = recursionLimit + RECURSION_LIMIT_INCREMENT) {
            // set recursion limit
            FHsort.setRecursionLimit(recursionLimit);

            raw.append(recursionLimit);
            System.out.print(recursionLimit);

            // loop through for different recursion limits
            for (int size = ARRAY_SIZE_START; size <= ARRAY_SIZE_MAX; size += ARRAY_SIZE_INCREMENT) {
                int timeInMS = 0;

                // create auto generated random array of correct size
                Integer[] randomArray = fillArrayRandom(size);

                // run at least 3 times
                for (int t = 0; t < 3; t++) {
                    // copy array to sort
                    Integer[] tempArray = randomArray.clone();

                    // measure start and stop time around the mergeSort function
                    startTime = System.nanoTime();
                    FHsort.mergeSort(tempArray);
                    stopTime = System.nanoTime();

                    // convert to ms, truncate to int
                    timeInMS += (int) ((stopTime - startTime) / NANO_TO_MILLI);

                }
                raw.append("\t");
                raw.append(timeInMS);

                System.out.print("\t" + timeInMS);
            }

            raw.append("\n");

            System.out.print("\n");

        }
        //System.out.println(raw.toString());


    }



}
