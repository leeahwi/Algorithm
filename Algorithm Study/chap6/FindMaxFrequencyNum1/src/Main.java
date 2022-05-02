import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static int[] count(int[] nums){
        int[] countnums = new int[2001];
        for(int i=0;i<nums.length;i++){
            countnums[nums[i]] += 1;
        }
        return countnums;
    }

    public static void solve(int[] count,int k){

        int c=0;
        int[] result = new int[k];

        for(int i=0;i<k;i++){
            int number = 0;
            int maxValue = 0;
            for(int q=0;q<count.length;q++){
                if(count[q]>maxValue){
                    number = q;
                    maxValue = count[q];
                }
            }
            count[number] = 0;
            result[c++] = number;
        }

        System.out.println(IntStream.of(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            solve(count(nums),2);

        }
    }
}
