import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void splitInversion(int[] nums,int lo, int mid, int hi){

        int[] Left = Arrays.copyOfRange(nums,lo,mid+1);
        int[] Right = Arrays.copyOfRange(nums,mid+1,hi+1);

        int L = 0;
        int R = 0;

        int i = lo;

        while(L<Left.length && R<Right.length) {
            if (Left[L] < Right[R]) {
                nums[i++] = Left[L];
                ++L;
            } else {
                nums[i++] = Right[R];
                ++R;
            }
        }

        while(L<Left.length){
            nums[i++] = Left[L++];
        }

        while(R<Right.length){
            nums[i++] = Right[R++];
        }
    }

    public static void inversion(int[] nums, int lo, int hi){

        if(hi!=lo){

            int mid = lo + (hi-lo)/2;
            //왼쪽 절반
            inversion(nums,lo,mid);
            //오른쪽 절반
            inversion(nums,mid+1,hi);
            //쪼개진 역상
            splitInversion(nums,lo,mid,hi);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){
            int n = Integer.parseInt(br.readLine().strip());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            inversion(nums,0,nums.length-1);

            System.out.println(nums.length);

            int mid = (nums.length-1)/2;;
            int L = mid;
            int R = nums.length-1;

            while(L>=0 || R>mid){
                if(L!=-1) System.out.printf("%d ",nums[L--]);
                if(R!=mid) System.out.printf("%d ",nums[R--]);
            }

            System.out.println();
        }
    }
}
