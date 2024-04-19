import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwoDimensionalArray extends OneDimensionalArray {
    protected int[][] arrays;

    public TwoDimensionalArray(int rows, int cols) {
        super(cols);
        arrays = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arrays[i][j] = random.nextInt(10) - 2;
            }
        }
    }

    public void processArray() {
//        if (arrays.length == arrays[0].length)
            processRectangular2DArray();
//        else
//            super.processNonRectangular2DArray();
    }

    public void processRectangular2DArray() {
        int[] maxRow = findMaxPositiveRow(arrays);
        List<int[]> resultList = new ArrayList<>();
        for (int[] row : arrays) {
            resultList.add(row);
            if (hasOddNegative(row)) {
                resultList.add(maxRow.clone());
            }
        }
        arrays = resultList.toArray(new int[resultList.size()][]);
    }

    @Override
    public String arrayToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrays.length; i++) {
            array = arrays[i];
            sb.append(super.arrayToString());
            sb.append("\n");
        }
        return sb.toString();
    }
}