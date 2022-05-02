import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    public static int splitInversion(int[] nums,int lo, int mid, int hi){

        int[] Left = Arrays.copyOfRange(nums,lo,mid+1);
        int[] Right = Arrays.copyOfRange(nums,mid+1,hi+1);
        int L = 0;
        int R = 0;
        int i = lo;
        int count = 0;
        while(L<Left.length && R<Right.length) {
            if (Left[L] < Right[R]) {
                nums[i++] = Left[L];
                ++L;
            } else {
                nums[i++] = Right[R];
                count += Left.length - L;
                ++R;
            }
        }
        while(L<Left.length){
            nums[i++] = Left[L++];
        }
        while(R<Right.length){
            nums[i++] = Right[R++];
        }
        return count;
    }

    public static int inversion(int[] nums, int lo, int hi){
        if(hi == lo){
            return 0;
        }
        else{
            int mid = lo + (hi-lo)/2;
            //왼쪽 절반
            int q = inversion(nums,lo,mid);
            //오른쪽 절반
            int k = inversion(nums,mid+1,hi);
            //쪼개진 역상
            int e = splitInversion(nums,lo,mid,hi);
            return q+k+e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine().strip());
        for(int i=0;i<test;++i){
            int n = Integer.parseInt(br.readLine().strip());
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(inversion(nums,0,nums.length-1));
        }
    }
}
