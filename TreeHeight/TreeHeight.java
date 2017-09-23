import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
            Node [] nodes = new Node[n];
            int index = 0, root = -1;

            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(i);
            }

            for (int child = 0; child < n ; child++) {
                int px = parent[index];
                if(px == -1) root = child;
                else {
                    nodes[px].addChild(nodes[child]);
                }
                index++;
            }



            return height(nodes, root);

        }

        private int height(Node[] nodes, int root) {
		    return 1 + height(nodes[root]);
        }

        private int height(Node x) {
		    int max = 0;
		    if(x == null) return 1;
		    while(!x.childs.isEmpty()) {
		        int candidate = 1 + height(x.childs.pop());
		        if(candidate > max) max = candidate;
            }
            return max;
        }
    }

	public class Node {
        public int value;
        public Stack<Node> childs;
        
        public Node(int v) {
            this.value = v;
            childs = new Stack<Node>();
        }

        public void addChild(Node x){
            childs.push(x);
        }
    }

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
