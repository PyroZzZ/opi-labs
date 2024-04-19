import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. Одномерный массив\n2. Двумерный массив");
        Scanner sc = new Scanner(System.in);

        switch (sc.nextInt()) {
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
                int rows = sc.nextInt();
                System.out.print("Столбцов - ");
                int columns = sc.nextInt();

                TwoDimensionalArray TDArray = new TwoDimensionalArray(rows, columns);

                System.out.println("Исходный массив:");
                System.out.println(TDArray.arrayToString());

                TDArray.processArray();

                System.out.println("Изменённый массив:");
                System.out.println(TDArray.arrayToString());
                break;
        }
    }
}