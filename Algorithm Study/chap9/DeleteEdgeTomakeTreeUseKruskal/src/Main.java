import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static class Edge {

        int startNode;
        int endNode;

        public Edge(int startNode, int endNode){

            this.startNode = startNode;
            this.endNode = endNode;
        }
    }

    public static void union(int[] parent, int a, int b) {

        a = find(parent, a);
        b = find(parent, b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }

    }

    public static int find(int[] parent, int x) {
        if (parent[x] == x)
            return x;
        else
            return find(parent, parent[x]);
    }

    public static void makeTree(ArrayList<Edge> edges, int[] parent){

        List<Edge> result = new ArrayList<>();

        for (int i = 0; i < edges.size(); i++) {

            if (find(parent,edges.get(i).startNode-1) != find(parent, edges.get(i).endNode-1)) {

                union(parent, edges.get(i).startNode-1,edges.get(i).endNode-1);
            }
            else{
                result.add(edges.get(i));
            }
        }

        Edge resultEdge = result.get(result.size()-1);
        System.out.printf("%d %d\n",resultEdge.startNode,resultEdge.endNode);
    }

    public static void solve(ArrayList<Edge> edges, int N){

        List<Edge> result = new ArrayList<>();

        for(int i=0;i<edges.size();i++){

            Edge resultEdge = edges.get(i);
            edges.remove(i);

            int[] parent = new int[N];
            for(int a=0;a<N;a++){
                parent[a] = a;
            }

//            if(makeTree(edges,parent)){
//                result.add(resultEdge);
//            }

            edges.add(i,resultEdge);

        }

        Edge resultEdge = result.get(result.size()-1);

        System.out.printf("%d %d\n",resultEdge.startNode,resultEdge.endNode);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            ArrayList<Edge> edges = new ArrayList<>(N);

            for(int k=0;k<edge.length;k+=2){

                edges.add(new Edge(edge[k],edge[k+1]));
                //edges.add(new Edge(edge[k+1],edge[k]));
            }

            //자기 자신 부모로
            int[] parent = new int[N];
            for(int a=0;a<N;a++){

                parent[a] = a;
            }

            makeTree(edges, parent);

        }
    }
}
