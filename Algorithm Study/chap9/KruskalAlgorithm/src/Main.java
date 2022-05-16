import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{

        int startNode;
        int endNode;
        int dist;

        public Edge(int startNode, int endNode, int dist){

            this.startNode = startNode;
            this.endNode = endNode;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist,o.dist);
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

    public static void solve(ArrayList<Edge> edges, int[] parent){

        int cost = 0;

        // 낮은 비용부터 크루스칼 알고리즘 진행
        for (int i = 0; i < edges.size(); i++) {

            // 사이클이 존재하지 않는 경우에만 간선을 선택한다
            if (find(parent,edges.get(i).startNode) != find(parent, edges.get(i).endNode)) {

                union(parent, edges.get(i).startNode,edges.get(i).endNode);
                cost += edges.get(i).dist;
            }
        }

        System.out.println(cost);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            ArrayList<Edge> edges = new ArrayList<>(E);

            for(int k=0;k<edge.length;k+=3){

                edges.add(new Edge(edge[k],edge[k+1],edge[k+2]));
                edges.add(new Edge(edge[k+1],edge[k],edge[k+2]));
            }

            //자기 자신 부모로
            int[] parent = new int[N];
            for(int a=0;a<N;a++){

                parent[a] = a;
            }

            //낮은 가중치 순 정렬
            edges.sort(Edge::compareTo);

            solve(edges,parent);

        }
    }
}
