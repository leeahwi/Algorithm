import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static ArrayList<Integer> list = new ArrayList<>();

    public static ArrayList<Integer> BestSumFunc(int m,int[] nums){
        ArrayList<Integer> best = null;
        if(m<0){
            return null;
        }
        if(m==0){
            return new ArrayList<>();
        }
        for(int a:nums){
            list = BestSumFunc(m-a,nums);
            if(list!=null){
                if(best==null || best.size()>list.size()+1){
                    list.add(a);
                    best = list;
                }
            }
        }
        return best;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());

        for(int i=0;i<test;i++){
            //두 정수값 받아와서 int 변환후에 nums에 저장
            int[] nums = Arrays.asList(br.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();

            int m = nums[0];
            int n = nums[1];

            nums = Arrays.asList(br.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();

            ArrayList<Integer> resultlist = BestSumFunc(m,nums);

            if(resultlist!=null) {
                System.out.print(resultlist.size());

                for (int a : resultlist) {
                    System.out.print(" ");
                    System.out.print(a);
                }
                System.out.println();
            }
            else
                System.out.println(-1);
        }
    }
}
