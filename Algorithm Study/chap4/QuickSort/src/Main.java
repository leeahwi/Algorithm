import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Random;

public class Main {

    public static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void partition(int[] nums, int left,int right){
        if(left>=right){
            return;
        }
        else{

            int temp;
            Random r = new Random();
            int p = r.nextInt(right - left) + left;
            swap(nums,left,p);
            int j = left+1;

            for(int i=left+1;i<=right;++i){
                //pivot보다 값 작은 경우
                if(nums[i]<nums[left]){
                    //swap
                    swap(nums,i,j);
                    ++j;
                }
            }

            swap(nums,j-1,left);

            partition(nums,left,j-2);
            partition(nums,j,right);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){
            int n = Integer.parseInt(br.readLine().strip());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            partition(nums,0,nums.length-1);

            System.out.println(Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        }
    }
}