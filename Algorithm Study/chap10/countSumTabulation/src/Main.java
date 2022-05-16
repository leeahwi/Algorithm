import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static long solve(int m, int[] numbers){

        long[] count = new long[m+1];
        Arrays.fill(count,0);
        count[0] = 1;

        for(int i=0;i<m;i++){
            if(count[i]!=0) {
                for (int n : numbers) {
                    if (i + n <= m) {

                        count[i + n] += count[i];
                    }
                }
            }
        }

        return count[m];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


            System.out.println(solve(m, numbers));

        }
    }
}
