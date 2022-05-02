import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            int n = Integer.parseInt(br.readLine().strip());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Arrays.sort(nums);

            System.out.println(nums.length);

            int[] res = new int[nums.length];

            int j = nums.length - 1;
            int a = 1;

            while (j >= 0) {
                if (a > nums.length - 1) {
                    a = 0;
                }

                res[a++] = nums[j--];
                a++;
            }
            for (a = 0; a < res.length; a++) {
                System.out.print(res[a]);
                System.out.print(" ");
            }

            System.out.println();
        }
    }
}
