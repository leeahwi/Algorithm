import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static class Pair{

        private int x, y;

        public Pair(int x,int y){
            this.x = x;
            this.y = y;
        }

        public int getPairx(){
            return this.x;
        }

        public int getPairy(){
            return this.y;
        }

        public static double calcdistance(Pair a, Pair b){
            return Math.sqrt(Math.pow((b.x - a.x),2) + Math.pow((b.y - a.y),2));
        }
    }

    public static double splitclosedpair(List<Pair> pairs,int mid,double alpha){

        int midpairx = pairs.get(mid).x;
        double best = alpha;

        //List<Pair> Lx = arr.subList(lo,mid+1).stream().filter(p -> midpairx - alpha < p.x && p.x < midpairx + alpha).toList();
        //List<Pair> Rx = arr.subList(mid+1,hi).stream().filter(p -> midpairx - alpha < p.x && p.x < midpairx + alpha).toList();
        //List<Pair> Ly = Lx.stream().sorted(Comparator.comparing(Pair::getPairy)).toList();
        //List<Pair> Ry = Rx.stream().sorted(Comparator.comparing(Pair::getPairy)).toList();

        pairs = pairs.stream().filter(p -> midpairx - alpha < p.x && p.x < midpairx + alpha).toList();
        List<Pair> L = pairs.stream().filter(p -> p.x <= midpairx).toList();
        List<Pair> R = pairs.stream().filter(p -> p.x > midpairx).toList();
        L = L.stream().sorted(Comparator.comparing(Pair::getPairy)).toList();
        R = R.stream().sorted(Comparator.comparing(Pair::getPairy)).toList();

        if(L.size() != 0 && R.size() != 0) {
            for (int i = 0; i < L.size(); i++) {
                int j = 0;
                while(j < R.size() && j < 7) {
                    double dis = Pair.calcdistance(L.get(i), R.get(j));
                    if (dis < best) {
                        best = dis;
                    }
                    j++;
                }
            }
        }

        return best;
    }

    public static double closedpair(List<Pair> pairs){

        if(pairs.size()<=3){
            double alpha = -1;
            int j = -1;
            if(j == -1){
                alpha = Pair.calcdistance(pairs.get(0),pairs.get(1));
                j++;
            }
            //j lo부터
            while(j<=pairs.size()-1){
                for(int i=j+1;i<=pairs.size()-1;i++){
                    double temp = Pair.calcdistance(pairs.get(j),pairs.get(i));
                    alpha = Math.min(alpha, temp);
                }
                j++;
            }
            return alpha;
        }
        else{
            int lo = 0;
            int hi = pairs.size()-1;

            int mid = lo + (hi-lo)/2;

            double a = closedpair(pairs.subList(lo,mid+1));
            double b = closedpair(pairs.subList(mid+1,hi+1));

            double n = Math.min(a,b);

            double c = splitclosedpair(pairs,mid,n);

            return Math.min(c,n);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            // 1~2500
            int n = Integer.parseInt(br.readLine().strip());

            //-10000~10000
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            List<Pair> list = new ArrayList<Pair>(n+1);

            for(int k=0;k<n;k++){
                list.add(new Pair(nums[k*2],nums[k*2+1]));
            }

            list = list.stream().sorted(Comparator.comparing(Pair::getPairx)).toList();

            if(list.size()==1){
                System.out.print(0.00);
            }
            else
                System.out.printf("%.2f%n",closedpair(list));

        }
        //1 2 2 9 9 1 4 2 5 0 3 4 2 5 1 7 1 4 5 5 4 8
        //-5 0 -3 4 2 1 3 4 -1 1 8 8 1 7
    }
}
