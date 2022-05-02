import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

public class Main {

    public static int findsplitmaxsection(int[] nums,int lo,int mid, int hi){

        int[] Left = Arrays.copyOfRange(nums,lo,mid+1);
        int[] Right = Arrays.copyOfRange(nums,mid+1,hi+1);
        int L = Left.length-1;
        int R = 0;
        int Lmax = Left[L];
        int Lsum = Lmax;
        int Rmax = Right[R];
        int Rsum = Rmax;
        //find L max
        while(L>0){
            Lsum += Left[--L];
            if(Lmax<Lsum){
                Lmax = Lsum;
            }
        }
        while(R<Right.length-1){
            Rsum += Right[++R];
            if(Rmax<Rsum){
                Rmax = Rsum;
            }
        }
        return Lmax+Rmax;
    }
    public static int findmaxsection(int[] nums, int lo, int hi){

        if(hi==lo){
            return nums[lo];
        }
        else{
            int max = 0;
            int mid = lo + (hi-lo)/2;
            int a = findmaxsection(nums,lo,mid);
            int b = findmaxsection(nums,mid+1,hi);
            int c = findsplitmaxsection(nums,lo,mid,hi);
            max = Math.max(a, b);
            max = Math.max(max,c);
            return max;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine().strip());
        for(int i=0;i<test;i++){
            int n = Integer.parseInt(br.readLine().strip());
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(findmaxsection(nums,0,nums.length-1));
        }

    }
}
