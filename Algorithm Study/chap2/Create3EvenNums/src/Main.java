import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    static int[] value = new int[3];

    public static List<Integer> Even3NumsFunc(int[] value, ArrayList<Integer> nums, int dep){

        if(dep==0){
            return new ArrayList<>();
        }

        List<Integer> list = null;

        for(int a=0;a<nums.size();a++){
            int q = nums.get(a);

            if(dep==3 && q==0){
            }
            else if(q==value[3-dep]){

                nums.remove(nums.indexOf(q));
                list = Even3NumsFunc(value,nums,--dep);
                nums.add(q);

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

            ArrayList<Integer> nums = new ArrayList<>();

            for(int j=0;j<num;j++){
                nums.add(Integer.parseInt(st.nextToken()));
            }

            for(int k=100;k<999;k+=2){

                int q = k;

                for(int h=2;h>=0;h--){
                    value[h] = q%10;
                    q /= 10;
                }

                List<Integer> tmp = Even3NumsFunc(value,nums,3);

                if(tmp!=null && tmp.size()==3){
                    int result = 0;
                    result = tmp.get(2)*100 + tmp.get(1)*10 + tmp.get(0);
                    System.out.print(result);
                    System.out.print(" ");
                }
            }

            System.out.println();
        }

    }
}
