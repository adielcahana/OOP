public class Sort {
    /**
    * @author adiel cahana <adielcahana@gmail.com>
    * @version 1.0
    * @since 2016-03-02 */
    public static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
    public static int partition(int[] arr, int first, int last) {
        int pivot = (first + last) / 2;
        int i = first, j = last - 2;
        swap(arr, last - 1, pivot);
        pivot = arr[last - 1];
        while (true) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
            } else {
                break;
            }
        }
        swap(arr, last - 1, i);
        return i;
    }

    public static void quicksort(int[] arr, int first, int last) {
        int pivot;
        if (last - first > 1) {
            pivot = partition(arr, first, last);
            quicksort(arr, first, pivot);
            quicksort(arr, pivot + 1, last);
        }
    }
    public static void main(String[] args) {
        int i = 0;
        String[] newArgs = new String[args.length - 1];
        while (i < newArgs.length) {
            newArgs[i] = args[i + 1];
            i++;
        }
        int[] arrToSort = DescribeNumbers.stringsToInts(newArgs);
        quicksort(arrToSort, 0, arrToSort.length);
        if (args[0].equals("asc")) {
            i = 0;
            while (i < arrToSort.length) {
                System.out.print(arrToSort[i] + " ");
                i++;
            }
        } else { //if (args[0].equals("desc"))
            i = arrToSort.length - 1;
            while (i >= 0) {
                System.out.print(arrToSort[i] + " ");
                i--;
            }
        }
        System.out.println();
    }
}
