import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();

      for (int i = data.length-1; i >= 0; --i) {
          siftDown(i);
      }
    }

    public void siftUp(int i) {
        while(i > 1 && data[i/2] > data[i]) {
            swap(i/2, i);
            i = data[i/2];
        }
    }

    public void siftDown(int i) {
        int min = i;
        int left = leftChild(i);
        int right = rightChild(i);
        if(left < data.length && data[left] < data[min]) {
            min = left;
        }

        if(right < data.length && data[right] < data[min]) {
            min = right;
        }

        if(i != min) {
            swap(i, min);
            siftDown(min);
        }
    }

    public int parent(int i) {
        return i/2;
    }

    public int leftChild(int i) {
        if(i == 0) return 1;
        else return (i*2) + 1;
    }

    public int rightChild(int i) {
        if(i == 0) return 2;
        else return (i*2) + 2;
    }

    public void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
        swaps.add(new Swap(i, j));
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
