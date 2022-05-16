import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

//not solved

public class Main {

    static class Edge{

        int startNode;
        int endNode;

        boolean isInput = false;

        public Edge(int startNode, int endNode,boolean isInput){

            this.startNode = startNode;
            this.endNode = endNode;
            this.isInput = isInput;
        }
    }


    public static void solve(ArrayList<Edge> edges, int N){

        Edge resultEdge = new Edge(-1,-1,false);

        boolean isCycle;
        boolean[] visited = new boolean[N];

        for(int i=0;i<edges.size();i++){

            isCycle = false;
            Arrays.fill(visited,false);

            Edge temp = edges.get(i);

            edges.remove(i);

            for(int k=0;k<edges.size();k++) {

                if (visited[edges.get(k).startNode-1] && visited[edges.get(k).endNode-1]) {

                    isCycle = true;
                    break;
                } else {
                    visited[edges.get(k).startNode-1] = true;
                    visited[edges.get(k).endNode-1] = true;
                }
            }

            for(int n=0;n<visited.length;n++){
                if(!visited[n]){
                    isCycle = true;
                }
            }

            edges.add(i,temp);

            if(!isCycle){
                resultEdge = temp;
            }
        }

        System.out.printf("%d %d",resultEdge.startNode,resultEdge.endNode);
        System.out.println();

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++) {

            int N = Integer.parseInt(br.readLine().strip());

            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            ArrayList<Edge> edges = new ArrayList<>();

            for(int k=0;k<edge.length;k+=2){

                edges.add(new Edge(edge[k],edge[k+1],true));
            }

            solve(edges, N);

        }
    }
}
