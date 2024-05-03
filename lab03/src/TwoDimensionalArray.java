import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TwoDimensionalArray extends OneDimensionalArray {
    protected OneDimensionalArray[] arrays;

    public TwoDimensionalArray(int rows, int cols) {
        super(1);
        arrays = new OneDimensionalArray[rows];
        for (int i = 0; i < rows; i++) {
            arrays[i] = new OneDimensionalArray(cols);
        }
    }

    public TwoDimensionalArray(int rows) {
        super(1);
        arrays = new OneDimensionalArray[rows];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < rows; i++) {
            arrays[i] = new OneDimensionalArray(sc.nextInt());
        }
    }

    public void processRectArray(int cols) {
        int max = -1;
        int maxIndex = 0;

        for (int i = 0; i < arrays.length; i++) {
            if (findMaxPositive(arrays[i].array.clone(), max) >= 0) {
                max = findMaxPositive(arrays[i].array, max);
                maxIndex = i;
            }
        }

        List<int[]> resultList = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            resultList.add(arrays[i].array);
            if (hasOddNegative(arrays[i].array)) {
                resultList.add(arrays[maxIndex].array);
            }
        }

        arrays = new OneDimensionalArray[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            arrays[i] = new OneDimensionalArray(cols);
        }

        for (int i = 0; i < resultList.size(); i++) {
            arrays[i].array = resultList.get(i);
        }
    }

    public void processNonRectArray() {
        int minArray = 0;
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i].array.length < arrays[i - 1].array.length) {
                minArray = i;
            }
        }

        System.out.println("Самый короткий массив:");
        System.out.println(arrays[minArray].arrayToString());
        arrays[minArray].processArray();
    }

    @Override
    public String arrayToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrays.length; i++) {
            array = arrays[i].array;
            sb.append(super.arrayToString());
            sb.append("\n");
        }
        return sb.toString();
    }
}