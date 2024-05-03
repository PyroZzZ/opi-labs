import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "lab04part1\\src\\in.txt";
        String outputFilePath = "lab04part1\\src\\out.txt";

        String line = readLine(inputFilePath);

        int result = numberFormat(line);
        writeFile(outputFilePath, result);
    }

    public static String readLine(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        String str = sc.nextLine();
        sc.close();
        return str;
    }

    public static int numberFormat(String str) {
        try {
            Integer.parseInt(str);
            return 1;
        } catch (NumberFormatException e) {}
        try {
            Double.parseDouble(str);
            return 2;
        } catch (NumberFormatException e) {}

        return 0;
    }

    public static void writeFile(String path, int out) throws IOException {
        BufferedWriter wr = new BufferedWriter(new FileWriter(path));
        wr.write(String.valueOf(out));
        wr.close();
    }
}