import java.util.Random;

// Удалить столбец, содержащий минимальное отрицательное
public class task02 {
    static int N = 5;
    static int M = 4;
    static int[][] arr = new int[N][M];

    public static void main(String[] args) {
        int tmp = 0;
        int tmp_col = 0;
        Random r = new Random();

        System.out.println("Source array:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = r.nextInt(100) - 50;
                System.out.printf("%4d", arr[i][j]);
            }
            System.out.println();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] < 0 && arr[i][j] <= tmp) {
                    tmp = arr[i][j];
                    tmp_col = j;
                }
            }
        }

        deleteColumn(tmp_col);

        System.out.println("\nChanged array:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%4d", arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void deleteColumn(int col) {
        if (col == M) {
            M--;
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = col; j < M; j++) {
                if (j + 1 < M) {
                    arr[i][j] = arr[i][j + 1];
                }
            }
        }
        M--;
    }
}