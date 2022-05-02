import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static List<Integer> howsum(int[] nums,int m){
        if(m<0){
            return null;
        }
        if(m==0){
            return new ArrayList<>();
        }

        List<Integer> list = null;

        for(int a:nums){
            list = howsum(nums,m-a);
            if(list!=null){
                list.add(a);
                return list;
            }
        }

        return list;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()," ");

            int[] nums = new int[n];

            for(int j=0;j<n;j++){
                nums[j] = Integer.parseInt(st.nextToken());
            }

            List<Integer> result = howsum(nums,m);

            if(result!=null){
                System.out.print(result.size());
                for(int a:result){
                    System.out.print(" ");
                    System.out.print(a);
                }
                System.out.println();
            }
            else{
                System.out.println(-1);
            }
        }
    }
}
