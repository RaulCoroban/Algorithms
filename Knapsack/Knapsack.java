import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int rows = w.length;
        int cols = W;
        int [][] C = new int [rows + 1][cols + 1];

        for (int i = 0; i < rows; i++) {
            C[i][0] = 0;
        }

        for (int i = 0; i < cols; i++) {
            C[0][i] = 0;
        }

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                if(w[r - 1] <= c) {
                    C[r][c] = Math.max(w[r-1] + C[r-1][c - w[r-1]], C[r-1][c]);
                }
                else {
                    C[r][c] = C [r - 1][c];
                }

            }
        }

        return C[rows][cols];
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

