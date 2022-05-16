import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Block implements Comparable<Block>{

        int value = 0;
        int weight = 0;

        public Block(int value, int weight){

            this.value = value;
            this.weight = weight;
        }

        public int getWeight(){
            return weight;
        }

        @Override
        public int compareTo(Block o){
            return Integer.compare(this.weight,o.weight);
        }
    }

    public static Integer[] solve(int w, Block[] blockArray) {

        Integer[] dp = new Integer[w + 1];
        boolean[] checkVisited = new boolean[blockArray.length];

        Arrays.fill(dp, null);
        dp[0] = 0;

        for (int i = 0; i < w; i++) {
            if (dp[i] != null) {
                for (int k=0;k<blockArray.length;k++) {
                    Block block = blockArray[k];
                    if(i+block.weight<=w && !checkVisited[k]) {
                        if (dp[i + block.weight] == null || dp[i + block.weight] < dp[i] + block.value) {
                            dp[i + block.weight] = dp[i] + block.value;
                        }
                    }
                }
            }

        }
        return dp;
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] blocks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Block[] blockArray = new Block[n];

            for(int k=0;k<n;k++){
                blockArray[k] = new Block(blocks[(k)*2],blocks[(k)*2+1]);
            }

            int max = Arrays.stream(solve(w,blockArray)).sorted(Comparator.naturalOrder()).mapToInt(Integer::intValue).toArray()[0];

            System.out.println(max);


        }
    }
}
