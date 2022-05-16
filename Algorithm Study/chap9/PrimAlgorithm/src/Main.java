import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{

        int node;
        int dist;

        public Edge(int node, int dist){

            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist,o.dist);
        }
    }

    static class Node {
        List<Edge> linkedNode = new ArrayList<>();

        public Node(){
        }

        public void putEdges(int node,int dist){

            this.linkedNode.add(new Edge(node,dist));
        }

        public List<Edge> getEdges(){

            return this.linkedNode;
        }

    }

    public static void solve(ArrayList<Node> nodes, boolean[] visited){

        int distNum = 0;
        int count = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        //일단 큐에 하나 넣는다
        pq.add(new Edge(0,0));

        while(!pq.isEmpty()){

            Edge edge = pq.poll();

            //방문했으면 continue;
            if(visited[edge.node]) continue;

            visited[edge.node] = true;

            distNum += edge.dist;
            for (Edge next : nodes.get(edge.node).linkedNode) {

                if(!visited[next.node]) {
                    pq.add(next);
                }
            }

            if (++count == nodes.size())
                break;
        }

        System.out.println(distNum);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[] edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            ArrayList<Node> nodes = new ArrayList<>(N);

            for(int k=0;k<N;k++){

                nodes.add(new Node());
            }

            for(int k=0;k<edges.length;k+=3){

                nodes.get(edges[k]).putEdges(edges[k+1],edges[k+2]);
                nodes.get(edges[k+1]).putEdges(edges[k],edges[k+2]);
            }

            boolean[] visited = new boolean[N];

            solve(nodes, visited);

        }
    }
}
