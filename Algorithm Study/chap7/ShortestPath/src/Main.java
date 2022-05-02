import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

    public static void solve(int[][] maps,int[][] grid){

        for (int j = 0; j < maps[0].length; j++) {
            if(j-1>=0)
                maps[0][j] += maps[0][j-1];
        }

        for (int j = 0; j < maps[0].length; j++) {
            if(j-1>=0)
                maps[j][0] += maps[j-1][0];
        }

        for(int i=1;i<maps.length;i++){
            for(int j=1;j<maps[i].length;j++){
                maps[i][j] = Math.min(maps[i-1][j],maps[i][j-1]) + grid[i][j];
            }
        }
    }


    public static void createMap(int[][] maps,int a,int[] W){

        for(int i=0;i<W.length;i++){
            maps[a][i] = W[i];
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[][] maps = new int[m][n];

            for(int a=0;a<m;a++){
                int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                createMap(maps,a,A);
            }

            int[][] grid = maps.clone();

            solve(maps,grid);

            System.out.println(maps[m-1][n-1]);


        }


    }
}
