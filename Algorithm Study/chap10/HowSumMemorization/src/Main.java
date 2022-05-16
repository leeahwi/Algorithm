import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    public static List<Integer> solve(int m, int[] numbers, List<List<Integer>> dp,int[] check) {

        if (m < 0) {
            return null;
        }
        if (m == 0) {
            return new ArrayList<>();
        }
        if (check[m]>=0) {
            return dp.get(m);
        }

        for (int n : numbers) {

            List<Integer> temp;

            temp = solve(m - n, numbers, dp,check);

            if (temp != null) {
                List<Integer> tempClone = new ArrayList<>(temp);
                check[m] = 1;
                tempClone.add(n);
                dp.set(m, tempClone);
                return tempClone;
            }
        }

        dp.set(m, null);
        check[m] = 0;
        return null;
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

            for(int k=0;k<m+1;k++){
                dp.add(null);
            }

            List<Integer> temp = new ArrayList<>();
            temp.add(0);
            dp.set(0, temp);

            int[] check = new int[m+1];
            Arrays.fill(check,-1);
            List<Integer> resultList = solve(m, numbers, dp,check);

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
