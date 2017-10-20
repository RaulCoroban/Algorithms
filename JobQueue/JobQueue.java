import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private long[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new long[jobs.length];
        startTime = new long[jobs.length];
        Worker[] nextFreeTime = new Worker[numWorkers];

        for (int i = 0; i < numWorkers; i++) {
            nextFreeTime[i] = new Worker(i,0);
        }

        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            Worker bestWorker = extractMin(nextFreeTime, duration);
            assignedWorker[i] = bestWorker.id;
            startTime[i] = bestWorker.duration;
        }
    }

    public void changePriority(Worker[] data, int i, int p) {
        int old = data[i].duration;
        data[i].duration += p;
        if(old > p) {
            siftUp(data, i);
        } else {
            siftDown(data, i);
        }

    }

    public Worker extractMin(Worker[] data, int duration) {
        Worker resw = data[0];
        Worker aux = new Worker(resw.id, resw.duration);
        changePriority(data, 0, duration);
        siftDown(data, 0);
        return aux;
    }

    public void siftUp(Worker[] data, int i) {
        while(i > 1 && data[parent(i)].duration >= data[i].duration && data[i/2].id > data[i].id) {
            swap(data, i/2, i);
            i = data[i/2].duration;
        }
    }

    public void siftDown(Worker[] data, int i) {
        int min = i;
        int left = leftChild(i);
        int right = rightChild(i);
        int aux = min;

        /*
        if(left < data.length && data[left].duration <= data[min].duration) {
                min1 = left;
        }

        if(right < data.length && data[right].duration <= data[min].duration) {
                min2 = right;
        }
        */

        if(left < data.length && data[min].compareTo(data[left]) > 0) {
            min = left;
        }

        if(right < data.length && data[aux].compareTo(data[right]) > 0) {
            if(data[right].compareTo(data[left]) < 0) min = right;
        }

        if(i != min) {
            swap(data, i, min);
            siftDown(data, min);
        }
    }

    public void insert(Worker[] data, Worker w) {
        int last = data.length-1;
        data[last] = w;
        siftUp(data, last);
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

    public void swap(Worker[] data, int i, int j) {
        Worker tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    static class Worker implements Comparable<Worker> {
        private int id;
        private int duration;

        public Worker(int id, int duration) {
            this.id = id;
            this.duration = duration;
        }

        @Override
        public int compareTo(Worker o) {
            int duration = this.duration - o.duration;
            if (duration != 0) {
                return duration;
            }
            return this.id - o.id;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
