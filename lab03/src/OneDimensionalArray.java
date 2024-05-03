import java.util.Random;

public class OneDimensionalArray {
    protected int[] array;

    public OneDimensionalArray(int size) {
        array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10) - 2;
        }
    }

    public void processArray() {
        int sumEven = 0;
        int sumOdd = 0;

        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                sumEven += array[i];
            }
            if (i % 2 == 1) {
                sumOdd += array[i];
            }
        }

        System.out.println("Сумма на чётных местах: " + sumEven);
        System.out.println("Сумма на нечётных местах: " + sumOdd);

        if (sumEven > sumOdd) {
            System.out.println("Сумма на чётных местах > на нечётных местах");
            return;
        }
        else if (sumEven < sumOdd) {
            System.out.println("Сумма на нечётных местах > на чётных местах");
            return;
        }

        System.out.println("Сумма элементов на нечётных и чётных местах одинаковая");
    }

    protected int findMaxPositive(int[] arr, int max) {
        boolean changed = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                changed = true;
            }
        }
        if (changed) return max;
        return -1;
    }

    protected boolean hasOddNegative(int[] row) {
        for (int num : row) {
            if (num < 0 && num % 2 != 0) {
                return true;
            }
        }
        return false;
    }

    public String arrayToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append(" ");
        }
        return sb.toString();
    }
}
