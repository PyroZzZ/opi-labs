import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int item = -1;

        while (item != 0) {
            System.out.println("\n1. Одномерный массив\n2. Двумерный прямоугольный массив\n3. Двумерный непрямоугольный массив");
            item = sc.nextInt();
            int rows;
            TwoDimensionalArray TDArray;

            switch (item) {
                case (1):
                    System.out.print("Размер массива: ");
                    int size = sc.nextInt();

                    OneDimensionalArray Array = new OneDimensionalArray(size);

                    System.out.println("Исходный массив:");
                    System.out.println(Array.arrayToString());
                    Array.processArray();
                    break;
                case (2):
                    System.out.print("Строк - ");
                    rows = sc.nextInt();
                    System.out.print("Столбцов - ");
                    int columns = sc.nextInt();

                    TDArray = new TwoDimensionalArray(rows, columns);

                    System.out.println("Исходный массив:");
                    System.out.println(TDArray.arrayToString());

                    TDArray.processRectArray(columns);

                    System.out.println("Изменённый массив:");
                    System.out.println(TDArray.arrayToString());
                    break;
                case (3):
                    System.out.print("Строк - ");
                    rows = sc.nextInt();

                    TDArray = new TwoDimensionalArray(rows);

                    System.out.println("Исходный массив:");
                    System.out.println(TDArray.arrayToString());

                    TDArray.processNonRectArray();

                    break;
            }
        }
    }
}