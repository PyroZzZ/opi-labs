import java.util.Random;

// Удалить из массива все элементы, встречающиеся ровно два или три раза
public class task01 {
    static int N = 10;
    static int[] arr = new int[N];

    public static void main(String[] args) {
        Random r = new Random();

        System.out.println("Source array:");
        //arr = new int[]{1, 2, 0, 4, 5, 6, 7, 8, 9, 0};
        //arr = new int[]{1,4,3,2,2,3,7,8,9,0};
        for (int i = 0; i < N; i++) {
            arr[i] = r.nextInt(10);
            System.out.printf("%2d", arr[i]);
        }

        // Поиск повторяющихся
        int tmp;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[i] == arr[j]) {
                    tmp = arr[i];
                    deleteEl(tmp);
                    deleteEl(tmp);
                    deleteEl(tmp);
                    if (i > 0) i--;
                }
            }
        }


        System.out.println("\nChanged array:");
        for (int i = 0; i < N; i++) {
            System.out.printf("%2d", arr[i]);
        }
    }

    public static void deleteEl(int value) {
        for (int i = 0; i < N; i++) {
            if (arr[i] == value) {
                if (i + 1 == N) {
                    N--;
                    return;
                }

                for (int j = i; j < N - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                N--;
                return;
            }
        }
    }
}
