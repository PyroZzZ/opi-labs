public class minEven {
    public static int find(int[] arr) {
        int tmp = 1000;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0 && arr[i] < tmp) {
                tmp = arr[i];
            }
        }
        return tmp;
    }
}