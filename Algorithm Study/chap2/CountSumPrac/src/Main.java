import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static List<Integer> solve(List<List<Integer>> l,int[] nums,int M){

        List<Integer> list = null;

        if(M<0){
            return null;
        }
        if(M==0){
            return new ArrayList<>();
        }
        for(int a:nums) {
            list = solve(l, nums, M - a);
            if(list!=null){
                list.add(a);
            }
        }

        if(list!=null) l.add(list);
        return list;

    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            List<List<Integer>> l = new ArrayList<>();
            solve(l,nums,M);

            if(M==0){
                System.out.println(0);
            }
            else if(l.size()==0){
                System.out.println(-1);
            }
            else {
                int min_index = 0;

                for (int a = 1; a < l.size(); a++) {
                    if (l.get(a).size() < l.get(min_index).size()) {
                        min_index = a;
                    }
                }

                System.out.printf("%d ", l.get(min_index).size());
                l.get(min_index).forEach(x -> System.out.printf("%d ", x));
                System.out.println();
            }

        }

    }
}
