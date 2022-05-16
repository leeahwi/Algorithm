import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class Main {

    public static class LRCCache{

        LinkedHashMap<Integer,Integer> map;
        int mapSize = 0;

        public LRCCache(int n){
            this.map = new LinkedHashMap<>();
            mapSize = n;
        }

        public void put(int k,int v){

            map.remove(k);

            if(mapSize <= map.size()){
                int olderKey = map.keySet().iterator().next();
                map.remove(olderKey);
            }
            map.put(k,v);
        }

        public int get(int k){
            if(!map.containsKey(k))
                return -1;

            int getValue = map.get(k);
            map.remove(k);
            map.put(k,getValue);

            return getValue;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            LRCCache LRC = new LRCCache(c);

            for(int k=0;k<arr.length;k++){
                if(arr[k]==0){
                    LRC.put(arr[k+1],arr[k+2]);
                    k+=2;
                }
                else if(arr[k]==1){
                    System.out.printf("%d ",LRC.get(arr[k+1]));
                    k++;
                }
            }

            System.out.println();

        }


    }
}
