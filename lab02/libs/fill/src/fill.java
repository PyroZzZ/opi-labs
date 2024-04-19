import java.util.Scanner;

public class fill {
    public static void fillArray(int[] arr) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
    }
}