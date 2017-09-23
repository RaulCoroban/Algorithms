import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

        private static int[] partition3(long[] array, int left, int right) {
            long x = array[left];
            int mid1 = left;
            int mid2 = right;

            int i = left + 1;

            while(i <= mid2) {
                if (array[i] > x) {
                    swap(array, i, mid2);
                    mid2--;
                } else if (array[i] < x) {
                    swap(array, mid1, i);
                    mid1++;
                    i++;
                } else {
                    i++;
                }
            }

            int[] m = {mid1, mid2};
            return m;
        }

    private static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void randomizedQuickSort(long[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        long t = a[l];
        a[l] = a[k];
        a[k] = t;
        int m[] = partition3(a, l, r);
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Stress Test
        System.out.println("\"0\": Manual input");
        System.out.println("\"1\": Stress Test");
        int selector = scanner.nextInt();
        switch (selector) {
            case 0:
                System.out.println("Manual input selected. Please proceed: \r");
                int n = scanner.nextInt();
                long[] a = new long[n];
                for (int i = 0; i < n; i++) {
                    a[i] = scanner.nextLong();
                }
                randomizedQuickSort(a, 0, n - 1);
                for (int i = 0; i < n; i++) {
                    System.out.print(a[i] + " ");
                }
                break;
            case 1:
                System.out.println("Stress Testing...");
                Random generator = new Random();
                boolean pass = true;
                while (pass) {
                    int size = generator.nextInt(50);
                    long[] aa = new long[size];
                    long[] b = new long[size];

                    for (int i = 0; i < size; i++) {
                        aa[i] = generator.nextInt(50);
                        b[i] = aa[i];
                    }

                    for (int i = 0; i < aa.length; ++i) {
                        System.out.print(aa[i] + " ");
                    }

                    randomizedQuickSort(aa, 0, aa.length - 1); //Three Partition Implementation
                    Arrays.sort(b);

                    if (Arrays.equals(aa, b)) {
                        System.out.println("Correct Answer");
                    } else {
                        pass = false;
                        System.out.println("Wrong!! \n\n Correct Answer: \n\n");

                        for (int i = 0; i < b.length; ++i) {
                            System.out.print(b[i] + " ");
                        }

                        System.out.println("");
                        System.out.println("\n\n Your Answer: \n\n");

                        for (int i = 0; i < aa.length; ++i) {
                            if(aa[i] != b[i]) System.out.print("(" + aa[i] + ") ");
                            else System.out.print(aa[i] + " ");
                        }

                    }
                }
                break;
        }
    }

}

