public class Main {
    public static void main(String[] args) {
        int[] arr = new int[5];
        fill.fillArray(arr);
        print.writeArray(arr);
        System.out.print("\nМинимальное четное: " + minEven.find(arr));
    }
}