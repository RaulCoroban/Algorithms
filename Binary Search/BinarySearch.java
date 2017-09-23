import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearch(int[] a, int x) {
        int left = 0, right = a.length;
        int res = binarySearch(a, left, right, x);
        return res;
    }

    private static int binarySearch(int[] a, int left, int right, int x) {
        if(left >= right) {
            return -1;
        }
        int mid = left + ((right - left) / 2);

        if(a[mid] == x) return mid;
        else if (a[mid] > x) {
            return binarySearch(a, left, mid, x);
        } else {
            return binarySearch(a, mid + 1, right, x);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Size of first list numbers
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        //Size of second list numbers to find
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }

        //Algorithm execution
        for (int i = 0; i < m; i++) {
            System.out.print(binarySearch(a, b[i]) + " ");
        }
    }
}
