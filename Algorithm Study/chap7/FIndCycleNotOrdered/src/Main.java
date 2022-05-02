import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int findGraph(int[][] map,int v,boolean[] visited,int num){

        Queue<Integer> queue = new LinkedList<Integer>();
        visited[v] = true;
        queue.add(v);

        while(!queue.isEmpty()){

            v = queue.poll();

            for(int i=1;i<map.length;i++){
                if(map[v][i]==1 && !visited[i]){
                    queue.add(i);
                    num++;
                    visited[i] = true;
                }
            }
        }
        return num;
    }

    public static void solve(int[][] map){
        int max = 0;
        int num = 0;

        boolean[] visited = new boolean[map.length];

        for(int i=0;i<visited.length;i++){
            if(visited[i]) continue;
            else{
                num++;
                int n = 1;
                int result = findGraph(map,i,visited,n);
                if(max<result){
                    max = result;
                }
            }

        }
        System.out.printf("%d %d\n",num,max);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if(E==0){
                int temp = br.read();
                System.out.printf("%d %d\n",0,1);
            }
            else{
                int[] edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int[][] map = new int[N][N];

                map[edges[0]][edges[1]] = 1;
                map[edges[1]][edges[0]] = 1;

                int k = 2;

                while(k<edges.length){
                    map[edges[k]][edges[k+1]] = 1;
                    map[edges[k+1]][edges[k]] = 1;
                    k += 2;
                }
                solve(map);
            }
        }
    }
}
