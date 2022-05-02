import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void printmaxmin(int[] nums, int n) {

        if (n == 1) {
            System.out.printf("%d %d", nums[0], nums[0]);

        }
        else {
            int max, min;

            //n==2 인경우 한번 비교후 종료
            if (nums[0] < nums[1]) {
                max = nums[1];
                min = nums[0];
            }
            else {
                max = nums[0];
                min = nums[1];
            }

            int k = 2;

            if(n!=2) {
                while (k < n - 1) {
                    //N N 비교
                    if (nums[k] < nums[k + 1]) {
                        //L L 비교
                        if (nums[k] < min) {
                            min = nums[k];
                        }
                        //W W 비교
                        if (nums[k + 1] > max) {
                            max = nums[k + 1];
                        }
                    }
                    // N N 비교, nums[k]>=nums[k+1]
                    else {
                        //L L 비교
                        if (nums[k + 1] < min) {
                            min = nums[k + 1];
                        }
                        //W W 비교
                        if (nums[k] > max) {
                            max = nums[k];
                        }
                    }
                    k += 2;
                }
                //홀수개 인경우
                if(k==n-1){
                    if(nums[k]<min){
                        min = nums[k];
                    }
                    if(nums[k]>max){
                        max = nums[k];
                    }
                }
            }
            System.out.printf("%d %d", max, min);
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            int n = Integer.parseInt(br.readLine().strip());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            printmaxmin(nums,n);
            System.out.println();
        }

    }
}
