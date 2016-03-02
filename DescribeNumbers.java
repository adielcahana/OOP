
public class DescribeNumbers {
    public static int[] stringsToInts(String[] numbers) {
        int size = numbers.length;
        int[] intNums = new int[size];
        int i = 0;
        while (i < size) {
            intNums[i] = Integer.parseInt(numbers[i]);
            i++;
            }
        return intNums;
    }

    public static int min(int[] numbers) {
        int min = numbers[0];
        int size = numbers.length;
        int i = 1;
        while (i < size) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
            i++;
        }
        return min;
    }

    public static int max(int[] numbers) {
        int max = numbers[0];
        int size = numbers.length;
        int i = 1;
        while (i < size) {
            if (max < numbers[i]) {
                max = numbers[i];
            }
            i++;
        }
        return max;
    }

    public static float avg(int[] numbers) {
        float avg = 0;
        int size = numbers.length;
        int i = 0;
        while (i < size) {
            avg += numbers[i];
            i++;
        }
        return avg / size;
    }

    public static void main(String[] args) {
        int[] num = stringsToInts(args);
        System.out.println("min: " + min(num));
        System.out.println("max: " + max(num));
        System.out.println("avg: " + avg(num));
    }
}
