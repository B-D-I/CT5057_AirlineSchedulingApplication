package glos.S4008324;

import java.util.Arrays;

/**
 * This class has been created to enable Radix sorting of an array. This is used for ordering the flight
 * departure dates
 */
final class DepartureDatesRadixSort {
    // get the maximum value in array
    static int get_max_val(int my_arr[], int arr_len) {
        int max_val = my_arr[0];
        for (int i = 1; i < arr_len; i++)
            if (my_arr[i] > max_val)
                max_val = my_arr[i];
        return max_val;
    }
    // method to count sort array according to the digits
    static void countSort(int my_arr[], int arr_len, int exp) {
        int result[] = new int[arr_len];
        int i;
        //output array
        int count[] = new int[10];
        Arrays.fill(count,0);
        // store the count occurrences in count array
        for (i = 0; i < arr_len; i++)
            count[ (my_arr[i]/exp)%10 ]++;
        // change count[i] to contains position of digit in output
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
        // build output array
        for (i = arr_len - 1; i >= 0; i--) {
            result[count[ (my_arr[i]/exp)%10 ] - 1] = my_arr[i];
            count[ (my_arr[i]/exp)%10 ]--;
        }
        // copy output array to arr[], so arr[] now contains sorted numbers according to digit
        for (i = 0; i < arr_len; i++)
            my_arr[i] = result[i];
    }
    /**
     * This method sorts an array using Radix sort
     * @param my_arr: array to be sorted
     * @param arr_len: the array length
     */
    static void radix_sort(int my_arr[], int arr_len) {
        int m = get_max_val(my_arr, arr_len);
        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(my_arr, arr_len, exp);
    }
}

