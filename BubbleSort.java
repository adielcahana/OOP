public class BubbleSort {

	public static void main(String[] args) {
		int i = 0, j = 0;
		//  asc/desc צריך להתייחס לארגומנט של 
		String[] newArgs = new String[args.length - 1];
		int[] arrToSort = DescribeNumbers.stringsToInts(args);
		int size = arrToSort.length;
		for (i = 0; i < size - 1; i++) {
			for (j = 0; j < size - i - 1; j++) {
				if (compare(arrToSort[j], arrToSort[j + 1]) > 0) {
					swap(arrToSort, j, j + 1);
				}
			}
		}
		for (i = 0; i < size; i++) {
			System.out.print(arrToSort[i] + " ");
		}
		System.out.println();
	}

	public static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}

	public static int compare(int x, int y) {
		if (x > y)
			return 1;
		if (x == y)
			return 0;
		return -1;
	}
}
