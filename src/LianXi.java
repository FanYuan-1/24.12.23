import java.util.Random;
import java.util.Scanner;

public class LianXi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int []{scanner.nextInt()};
        System.out.println(toString(arr));


    }
    public static String toString(int[] arr){
        String result = "[";
        int i = 0;
        while (i < arr.length){
            result = result + arr[i] ;
            if(i < arr.length - 1){
                result = result + ",";
            }
            i++;
        }
        result = result + "]";
        return result ;
    }
}
//String numStr = row[j] == 0? "  " : (row[j] < 10 ? row[j] + " " : row[j] + "");
/*
int[] emptyIndex = new int[]{0, 0};
        for (int i = 0; i < boards.length; i++) {
int[] row = boards[i];
            for (int j = 0; j < row.length; j++) {
        if(row[j] == 0){
emptyIndex = new int[]{i, j};*/
