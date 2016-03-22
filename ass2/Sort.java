package ass2;

/**
* @author Ori Engelberg <turht50@gmail.com>
* @version 1.0
* @since 2016-03-03
*/
public class Sort {
    /**
    * Get an array and sort it asc or desc.
    * <p>
    * Get a string array with asc/desc and numbers, make it int array and sort
    * it with BubbleSort.
    * <p>
    * @param nums - string array of numbers.
    */
    public static  int[] bubbleSort(String[] nums) {
        int i = 0, j = 0;
        int[] arrToSort = DescribeNumbers.stringsToInts(nums);
        int size = arrToSort.length;
        for (i = 0; i < size - 1; i++) {
            for (j = 0; j < size - i - 1; j++) {
                if (compare(arrToSort[j], arrToSort[j + 1]) > 0) {
                    swap(arrToSort, j, j + 1);
                    }
                }
            }
        return arrToSort;
        }

    /**
    * Swap between to 2 index in array.
    * <p>
    * Get a string array and swap between 2 index by use in a temporary
    * integer.
    * <p>
    * @param arr - array of integers
    * @param idx1 - integer, index of the array.
    * @param idx2 - integer, index of the array.
    */
    public static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
    /**
    * compare between to 2 integers.
    * <p>
    * Get 2 integers and compare between them.
    * <p>
    * @param x - integer
    * @param y - integer
    * @return integer.
    */
    public static int compare(int x, int y) {
        if (x > y) {
            return 1;
            }
        if (x == y) {
            return 0;
            }
        return -1;
    }
}