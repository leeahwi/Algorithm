import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static boolean solve(int[][] map,int[] node,boolean[] visited, int Nodes_num){

        for(int n:node){
            if(visited[n]) continue;
            else {
                boolean[] visiting = new boolean[Nodes_num];
                if(findCycle(map, n, visited,visiting)) return true;
            }
        }
        return false;
    }

    public static boolean findCycle(int[][] map,int node,boolean[] visited,boolean[] visiting){

        boolean ok = false;
        visiting[node] = true;
        for (int i = 0; i < map.length; i++) {   //방문 노드에서 간선 따라 재귀 시행
            if (map[node][i] == 1) {
                if(node==i||visited[i])  continue;
                if(visiting[i]) return true;
                if(findCycle(map, i, visited,visiting)) {
                    ok = true;
                }
            }
        }
        visited[node] = true;

        return ok;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int Nodes_num = Integer.parseInt(st.nextToken());
            int Edges_num = Integer.parseInt(st.nextToken());

            if(Edges_num==0){
                int temp = br.read();
                System.out.println(false);
            }
            else{

                int[] nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int[][] map = new int[Nodes_num][Nodes_num];


                map[nodes[0]][nodes[1]] = 1;

                int k = 2;

                while(k<=nodes.length-1){
                    map[nodes[k]][nodes[k+1]] = 1;
                    k += 2;
                }

                boolean[] visited = new boolean[map.length];    //기본 false 초기화
                System.out.println(solve(map,nodes,visited,Nodes_num));
            }

        }
    }
}

