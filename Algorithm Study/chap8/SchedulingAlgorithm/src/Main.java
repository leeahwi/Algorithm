import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static class Pair implements Comparable<Pair>{

        int index;
        int time;
        int point;

        public Pair(int i, int t,int p){
            this.index = i;
            this.time = t;
            this.point = p;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.point==o.point){
                return Integer.compare(this.index,o.index);
            }
            else{
                return Integer.compare(o.point,this.point);
            }
        }

    }
    public static boolean feasible(List<Pair> pairs, int n){

        for(int i=1;i<pairs.size();i++){
            if(pairs.get(i).time < i+1){
                return false;
            }
        }
        return true;
    }

    public static void solve(Pair[] pair,int n){
       List<Pair> pairs = new ArrayList<>();
       List<Pair> J = new ArrayList<>();
       pairs.add(pair[0]);

       for(int i=1;i<pair.length;i++){
           pairs.add(pair[i]);
           pairs.sort(Comparator.comparingInt(x->x.time));
           if(!feasible(pairs,n)){
               pairs.remove(pair[i]);
           }

           J = pairs;
           if(J.size()==n/2)
               break;
       }

       J.sort(Comparator.comparingInt(x -> x.index));

       J.forEach(x-> System.out.printf("%d ",x.index));

    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            int n = Integer.parseInt(br.readLine().strip());

            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Pair[] pair = new Pair[n];

            int count = 0;
            for(int k=0;k<arr.length;k+=2){
                pair[count++] = new Pair(count, arr[k],arr[k+1]);
            }

            Arrays.sort(pair);

            solve(pair,n);

            System.out.println();

        }
    }
}
