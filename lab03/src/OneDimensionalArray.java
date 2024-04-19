import java.util.Random;

public class OneDimensionalArray {
    protected int[] array;

    public OneDimensionalArray(int size) {
        array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
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

        if (sumEven > sumOdd) {
            System.out.println("Чётных > нечётных");
            return;
        }
        else if (sumEven < sumOdd) {
            System.out.println("Нечётных > чётных");
            return;
        }

        System.out.println("Элементов поровну");
    }

    protected int[] findMaxPositiveRow(int[][] arrays) {
        int maxPositive = 0;
        int[] maxRow = null;
        for (int[] row : arrays) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] > maxPositive) {
                    maxPositive = row[i];
                    maxRow = row.clone();
                }
            }
        }
        return maxRow;
    }

    protected boolean hasOddNegative(int[] row) {
        for (int num : row) {
            if (num < 0 && num % 2 != 0) {
                return true;
            }
        }
        return false;
    }

    public void processNonRectangular2DArray() {

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
