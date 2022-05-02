import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

    public static void swap(int[] nums,int w,int t){
        int temp = nums[w];
        nums[w] = nums[t];
        nums[t] = temp;
    }
    public static void partition(int[] nums,int lo,int hi,int findindex){

        if(hi<=lo){
            if(hi+1==findindex){
                System.out.println(nums[hi]);
                return;
            }
            else if(lo+1==findindex){
                System.out.println(nums[lo]);
                return;
            }
        }
        else {

            Random r = new Random();
            int pivot = r.nextInt(hi - lo) + lo;


            int i=lo+1,j=lo+1;

            swap(nums,lo,pivot);


            for(i=lo+1;i<=hi;i++){

                if(nums[lo]>nums[i]){
                    swap(nums,j,i);
                    j++;
                }
            }
            //pivot 위치 순번으로
            swap(nums,lo,j-1);
            if(j==findindex){
                System.out.println(nums[j-1]);
            }
            else if(j<findindex){
                partition(nums,j,hi,findindex);
            }
            else{
                partition(nums,lo,j-2,findindex);
            }
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int finding = Integer.parseInt(st.nextToken());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            partition(nums,0,nums.length-1,finding);

        }
    }
}
