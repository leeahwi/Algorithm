import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static boolean findCycle(int[][] map,boolean[] visited,int v,Set<Integer> visiting){

        visiting.add(v);

        for(int i=0;i<visited.length;++i){
            if(v==i||visited[i]||map[v][i]!=1){
                continue;
            }
            if(visiting.contains(i)) return true;
            if(findCycle(map,visited,i,visiting)) return true;
        }
        visited[v] = true;
        return false;
    }


    public static boolean solve(int[][] map){

        boolean[] visited = new boolean[map.length];
        Set<Integer> visiting = new HashSet<>();

        for(int i=0;i<visited.length;i++) {
            visiting.clear();
            if(!visited[i]&&findCycle(map,visited,i,visiting))
                return true;
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


            if(Edges_num==0){
                int temp = br.read();
                System.out.println(false);
            }
            else {

                int[] nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int[][] map = new int[Nodes_num][Nodes_num];

                map[nodes[0]][nodes[1]] = 1;

                int k = 2;

                while (k<nodes.length) {
                    map[nodes[k]][nodes[k + 1]] = 1;
                    k += 2;
                }

                System.out.println(solve(map));
            }
        }
    }
}

