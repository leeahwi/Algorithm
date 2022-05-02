import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

    static int[] value = new int[3];
    static int[] nums = null;

    public static List<Integer> Even3NumsFunc(int[] value, int dep){

        if(dep==0){
            return new ArrayList<>();
        }

        List<Integer> list = null;

        //int i=0;
        //if(dep==3){
        //  if(nums[i]==0) ++i;

        for(int a=0;a<nums.length;a++){
            int q = nums[a];

            if(dep==3 && q==0){
            }
            else if(q==value[3-dep]){

                nums[a] = -1;
                list = Even3NumsFunc(value,--dep);
                nums[a] = q;

                if(list!=null){
                    list.add(q);
                    return list;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            int num = Integer.parseInt(br.readLine().strip());

            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            nums = new int[num];

            for(int j=0;j<num;j++){
                nums[j] = Integer.parseInt(st.nextToken());
            }

            int[] resultList = new int[500];
            int count=0;

            for(int k=100;k<999;k+=2){
                int q = k;

                for(int h=2;h>=0;h--){
                    value[h] = q%10;
                    q /= 10;
                }

                List<Integer> tmp = Even3NumsFunc(value,3);

                if(tmp!=null && tmp.size()==3){
                    int result = 0;
                    result = tmp.get(2)*100 + tmp.get(1)*10 + tmp.get(0);
                    resultList[count++] = result;
                }
            }
            System.out.println(IntStream.of(resultList).filter(x -> x!=0).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }

    }
}
