import java.lang.Math;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class SearchMethods {

    static int count = 0;

    static int binarySearch(int[] nums, int target) {
        int N = nums.length;
        count = 0;
        
        if (N == 0) {
            count++;
            return -1;
        }
        int left = 0;
        int right = N - 1;
        int midpoint;

        count++;
        while (left <= right) {
            midpoint = left + (right - left) / 2;
            
            if (nums[midpoint] == target) {
                count++;
                return count;
            } else if (nums[midpoint] > target) {
                count++;
                right = midpoint - 1;
            } else {
                count++;
                left = midpoint + 1;
            }
        }
        return -1;
    }

    // -- derived from https://www.geeksforgeeks.org/fibonacci-search/
	static int fibonacciSearch(int[] list, int target) {
		int N = list.length;
		count = 0;
        // -- degenerate case
        if (N == 1 && list[N-1] == target)
            return N-1;
		
	    // --Initialize fibonacci numbers 
        int m2 = 0; 
        int m1 = 1; 
        int m = 1; 

        // -- find the smallest fibonacci number greater than or
        //    equal to the list size (along with the
        //    two previous fibonacci numbers)
        while (m < N) {
            m2 = m1;
            m1 = m;
            m = m2 + m1;
        }
  
        // -- keep track of the discarded elements of the front of the list
        //    initially they are all kept
        int start = -1;
  
        // -- search for the target value within the interval 
        //    [m2..N]
        while (m > 1) {
            // -- is m2 within the list bounds?
        	int index = (start + m2 < N - 1) ? start + m2 : N - 1;

        	// -- if the target is greater than the value at m2 + offset
        	//    drop the lower portion of the array (the target is not there)
        	++count;
            if (target > list[index]) {
                m = m1;
                m1 = m2;
                m2 = m - m1;
                start = index;
            }
        	// -- else if the target is less than the value at m2 + offset
        	//    drop the upper portion of the array (the target is not there)
            else {
            	if (target < list[index]) {            
	                m = m2;
	                m1 = m1 - m2;
	                m2 = m - m1;
	            }
	            else {
	                return index;
	            }
            }
        }
  
        // -- target was not in the list
        return -1;
	}
	
    public static void write(double[] array) throws FileNotFoundException {
        PrintWriter outputFile = new PrintWriter("exportedData.txt");
        for (int i = 0; i < array.length; i++) {
            outputFile.println(i + "," + array[i]);
        }
        outputFile.close();
    }
    public static void main(String args[]) throws FileNotFoundException {

        int size = 20;
        double[] output = new double[size + 1];

        for (int i = 1; i <= size; i++) {
            int N = (int) Math.pow(2, i);
            int[] nums = new int[N];

            for (int j = 0; j < nums.length; j++) {
                nums[j] = j;
            }
            
            int sumOfOperations = 0;

            for (int curr = 0; curr < N; curr++) {
                int result = binarySearch(nums, curr);
                if (result == -1) {
                    System.out.println("not found");
                } else {
                    sumOfOperations += result;
                }
            }

            output[i] = sumOfOperations / (double) N;
        }

        // SearchMethods.write(output);
        //Binary Search output


        double[] fibOutput = new double[size + 1];
        
        for (int i = 1; i <= size; i++) {
            int N = (int) Math.pow(2, i);
            int[] nums = new int[N];

            for (int j = 0; j < nums.length; j++) {
                nums[j] = j;
            }
            
            int sumOfOperations = 0;

            for (int curr = 0; curr < N; curr++) {
                int result = fibonacciSearch(nums, curr);
                if (result == -1) {
                    System.out.println("not found");
                } else {
                    sumOfOperations += count;
                }
            }

            fibOutput[i] = sumOfOperations / (double) N;
        }

        SearchMethods.write(fibOutput);
        //Fibonacci Search output
    }
}