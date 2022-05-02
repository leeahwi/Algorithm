import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static List<Integer> solve(int[] nums,int M){

        List<Integer> list = null;

        if(M<0){
            return null;
        }
        if(M==0){
            return new ArrayList<>();
        }
        for(int a:nums){
            list = solve(nums,M-a);
            if(list!=null) {
                list.add(a);
                return list;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            List<Integer> list = solve(nums,M);

            if(list==null) System.out.println(-1);
            else {
                System.out.print(list.size());
                list.forEach(x -> System.out.printf(" %d",x));
                System.out.println();
            }
        }

    }
}
