import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static int Max = 999999;

    static class Node implements Comparable<Node> {
        private int weight;
        private int index;


        public Node(int weight,int index){
            this.weight = weight;
            this.index = index;
        }

        @Override
        public int compareTo(Node node){
            return Integer.compare(this.weight,node.weight);
        }
    }

    public static int[] solve(int n,int v,int[][] maps){

        PriorityQueue<Node> queue = new PriorityQueue<>();

        int distance[] = new int[n];
        boolean[] visited = new boolean[n];

        for(int i=0;i<n;++i){
            distance[i] = Max;
        }

        queue.add(new Node(0,v));

        distance[v] = 0;
        visited[v] = true;

        for(int i =0;i<n;++i){
            if(!visited[i]&&maps[v][i]!=Max){
                distance[i] = maps[v][i];
                queue.add(new Node(maps[v][i],i));
            }
        }

        while(!queue.isEmpty()){

            int min_index = -1;
            Node node = queue.poll();
            int min = node.weight;
            min_index = node.index;

            visited[min_index] = true;
            for(int k=0;k<n;++k){
                if(!visited[k]&&maps[min_index][k]!=Max){
                    if(distance[min_index] + maps[min_index][k] < distance[k]){
                        distance[k] = distance[min_index] + maps[min_index][k];
                        queue.add(new Node(distance[k],k));
                    }
                }
            }
        }
        for(int a=0;a<distance.length;a++){
            if(distance[a]==Max){
                distance[a] = -1;
            }
        }
        return distance;
    }
    public static void input(int[][] maps, int i,int j,int w){
        maps[i][j] = w;
    }
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] nodes = new int[K];

            int x=0;
            while(st.hasMoreTokens()){
                nodes[x++] = Integer.parseInt(st.nextToken());
            }
            if(E==0){
                int temp = br.read();

                int[] distance = new int[N];
                Arrays.fill(distance, -1);

                for(int a=0;a< nodes.length;a++){
                    if(nodes[a]==S) distance[S] = 0;
                }

                int[] result = new int[K];
                int o = 0;
                for (int a:nodes) {
                    result[o++] = distance[a];
                }
                String a = IntStream.of(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
                System.out.println(a);
            }
            else {

                int[] Edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int[][] maps = new int[N][N];

                for (int q = 0; q < maps.length; ++q) {
                    for (int j = 0; j < maps[0].length; ++j) {
                        maps[q][j] = Max;
                    }
                }
                int k = 0;
                while (k < Edges.length) {
                    input(maps, Edges[k], Edges[k + 1], Edges[k + 2]);
                    k += 3;
                }
                int[] distance = solve(N, S, maps);
                int[] result = new int[K];
                int o = 0;
                for (int a:nodes) {
                    result[o++] = distance[a];
                }
                String s = IntStream.of(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
                System.out.println(s);
            }
        }

    }
}
