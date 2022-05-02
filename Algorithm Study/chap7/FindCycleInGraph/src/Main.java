import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static void solve(int[][] map,int[] node,boolean[] visited){

        for(int n:node){
            if(visited[n]!=false) continue;
            else {
                if(findCycle(map, n, visited)) System.out.println(true);
                else System.out.println(false);
            }
        }

    }

    public static boolean findCycle(int[][] map,int node,boolean[] visited){

        boolean ok = false;

        if(visited[node]==true)
            return true;
        else {

            if (visited[node] == false) {

                visited[node] = true;   //node 방문 처리

                for (int i = 0; i < map.length; i++) {   //방문 노드에서 간선 따라 재귀 시행
                    if (map[node][i] == 1) {
                        if(findCycle(map, i, visited))
                            ok = true;
                    }
                }
            }
            else continue;


        }
        return false;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int Nodes_num = Integer.parseInt(st.nextToken());
            int Edges_num = Integer.parseInt(st.nextToken());

            int[] nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[][] map = new int[Nodes_num][Nodes_num];

            int k = 0;

            while(k<nodes.length-1){
                map[nodes[k]][nodes[k+1]] = 1;
                k += 2;
            }

            boolean[] visited = new boolean[map.length];    //기본 false 초기화
            solve(map,nodes,visited);

        }
    }
}
