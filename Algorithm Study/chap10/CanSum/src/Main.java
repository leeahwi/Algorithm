import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static boolean solve(int m,int[] numbers,int[] dp){

        if(m==0){
            return true;
        }
        if(m<0){
            return false;
        }
        if(dp[m]==1){
            return true;
        }
        else if(dp[m]==0){
            return false;
        }

        for(int n:numbers){

            if(solve(m-n,numbers,dp)){
                dp[m] = 1;
                return true;
            }
        }

        dp[m] = 0;
        return false;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] dp = new int[1001];
            dp[0] = 1;

            Arrays.fill(dp,-1);

            for(int num:numbers){
                dp[num] = 1;
            }

            System.out.println(solve(m,numbers,dp));
        }
    }
}
