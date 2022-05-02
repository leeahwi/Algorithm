import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int[] nums = null;

    //static ArrayList<En> map =

    //right = nums.lenght - 1 이어야함

    public static int splitInversion(int[] nums,int lo, int mid, int hi){

        int[] Left = Arrays.copyOfRange(nums,lo,mid+1);
        int[] Right = Arrays.copyOfRange(nums,mid+1,hi);

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

        if(nums.length == 1){
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

        //solve 영역

        //시간초과 O(n^2)
//        for(int i=left;i<=mid;i++){
//
//            for(int j=mid+1;j<=right;j++){
//                //역쌍일때
//                if(nums[i]>nums[j]){
//                    //map.put(nums[i],nums[j]);
//                    count++;
//                }
//            }
//        }

//        int a = left;
//        int b = mid;
//
//        for(int j=left;j<=right;j++) {
//
//            if(nums[a]<=nums[b]){
//                nums[j] = nums[a++];
//            }
//            // nums[a]>nums[b] -> right < left
//            else{
//                nums[j] = nums[b++];
//                count += (mid-a)+1;
//            }
//        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        //1 <= test <= 100
        for(int i=0;i<test;++i){

            // 1 <= N <= 10000
            int n = Integer.parseInt(br.readLine().strip());

            // 32비트 정수
            nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            System.out.println(inversion(nums,0,nums.length-1));

            //System.out.println(map.size());
            //System.out.println();

        }


    }
}
