import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void findmaxfreq(int[] nums,int k){

        if(nums.length==1){
            System.out.println(nums[0]);
            return;
        }

        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }
            else{
                map.put(nums[i],map.get(nums[i])+1);
            }
        }

        List<Map.Entry<Integer,Integer>> list_entries = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());

        Collections.sort(list_entries, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        List<Integer> list = list_entries.stream()
                .map(x -> x.getKey())
                .limit(k)
                .collect(Collectors.toList());

        System.out.println(IntStream.of(list.stream().mapToInt(Integer::intValue).toArray()).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            findmaxfreq(nums,k);

        }

    }
}
