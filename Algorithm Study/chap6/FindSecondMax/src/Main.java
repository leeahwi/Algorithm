import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static class num{

        int value;
        ArrayList<Integer> loselist;

        public void add(int value){
            this.loselist.add(value);
        }

        public num(int value){
            this.value = value;
            this.loselist = new ArrayList<Integer>();
        }
    }

    public static int[] printsecondmax(num[] v){

        num max;

        if(v.length==1){
            max = v[0];
            max.add(v[0].value);
        }
        else {

            if(v[0].value<v[1].value){
                max = v[1];
                v[1].add(v[0].value);
            }
            else{
                max = v[0];
                v[0].add(v[1].value);
            }

            int k = 2;

            if(v.length>2){

                while(k<v.length-1){
                    if(v[k].value>v[k+1].value){
                        v[k].add(v[k+1].value);
                        if(v[k].value>max.value){
                            v[k].add(max.value);
                            max = v[k];
                        }
                        else if(v[k].value<max.value){
                            max.add(v[k].value);
                        }
                    }
                    else {
                        v[k+1].add(v[k].value);
                        if(v[k+1].value>max.value){
                            v[k+1].add(max.value);
                            max = v[k+1];
                        }
                        else if(v[k+1].value<max.value){
                            max.add(v[k+1].value);
                        }
                    }

                    k += 2;
                }
                if(k==v.length-1){
                    if(v[k].value > max.value){
                        v[k].add(max.value);
                        max = v[k];
                    }
                    else if(v[k].value < max.value) {
                        max.add(v[k].value);
                    }
                }
            }
        }
        return max.loselist.stream().mapToInt(x->x).toArray();
    }

    public static void printmax(int[] nums, int n) {

        if (n == 1) {
            System.out.printf("%d", nums[0]);
        }
        else {
            int max;

            //n==2 인경우 한번 비교후 종료
            if (nums[0] < nums[1]) {
                max = nums[1];
            }
            else {
                max = nums[0];
            }

            int k = 2;

            if(n!=2) {
                while (k < n - 1) {
                    //N N 비교
                    if (nums[k] < nums[k + 1]) {
                        //W W 비교
                        if (nums[k + 1] > max) {
                            max = nums[k + 1];
                        }
                    }
                    // N N 비교, nums[k]>=nums[k+1]
                    else {
                        //W W 비교
                        if (nums[k] > max) {
                            max = nums[k];
                        }
                    }
                    k += 2;
                }
                //홀수개 인경우
                if(k==n-1){
                    if(nums[k]>max){
                        max = nums[k];
                    }
                }
            }
            System.out.printf("%d", max);
        }
        System.out.println();
    }



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            int n = Integer.parseInt(br.readLine().strip());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            num[] V = new num[n];

            for(int k=0;k<n;k++){
                V[k] = new num(nums[k]);
            }

            int[] lostnums = printsecondmax(V);
            //System.out.print(IntStream.of(lostnums).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            printmax(lostnums,lostnums.length);

        }

    }
}
