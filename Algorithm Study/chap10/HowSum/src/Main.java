import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static List<Integer> solve(int m, int[] numbers, List<List<Integer>> dp) {

        for(int i=0;i<m;i++){
            if(dp.get(i)!=null){
                for (int n : numbers) {

                    if (i + n <= m && dp.get(i + n) == null) {
                        List<Integer> list = new ArrayList<>(dp.get(i));
                        list.add(n);
                        dp.set(i+n, list);
                        if (i + n == m)
                            break;
                    }
                }
            }
        }

        return dp.get(m);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for (int i = 0; i < test; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            ArrayList<List<Integer>> dp = new ArrayList<>();

            dp.add(new ArrayList<>());

            for(int k=0;k<m+1;k++){
                dp.add(null);
            }

            List<Integer> resultList = solve(m, numbers, dp);

            if(resultList==null){
                System.out.println(-1);
            }
            else{
                System.out.printf("%d ",resultList.size());
                resultList.forEach(x-> System.out.printf("%d ",x));
                System.out.println();
            }
        }
    }
}
