import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    //크기 3 이하일시 전수조사 함수
    public static double getMinDistance(List<Pair> list){
        //일단 첫값 넣는다
        double min = Pair.calcdistance(list.get(0),list.get(1));
        int size = list.size();

        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                double temp = Pair.calcdistance(list.get(i),list.get(j));
                min = Math.min(min,temp);
            }
        }
        return min;
    }

    public static double splitclosedpair(List<Pair> px,List<Pair> py,double delta){

        double best = delta;
        Pair pmid = px.get(px.size()/2-1);

        List<Pair> sy = py.stream().filter(p -> p.x < pmid.x + delta && p.x > pmid.x - delta).toList();

        for(int i=0;i<sy.size();i++){
            int j=i+1;
            //여기서 y값들 2개 빼서 delta 보다 크면 break
            while(j < i+7 && j < sy.size()){
                double temp = Pair.calcdistance(sy.get(i),sy.get(j));
                best = Math.min(best,temp);
                j++;
            }
        }

        return best;
    }

    public static double closedpair(List<Pair> px, List<Pair> py){

        if(px.size()<=3){
            return getMinDistance(px);
        }
        else{

            int mid = (px.size()/2-1);
            //여기서 반으로 쪼개야함
            List<Pair> Lx = px.subList(0,(px.size()/2));
            List<Pair> Rx = px.subList((px.size()/2),px.size());

            //여기서 Ly 와 Rx는 선형에 구해야함
            //Ly, Ry를 구하는 방법은 px의 중앙 x값보다 작거나 Ly로, 크면 Ry로 보내면 된다.
            //x가 중앙값 x와 같다면? y를 비교해서 중앙값 (a,b) 일때, a==x 일시 y<=b 면 Ly y>b 면 Ry로 보낸다.

            List<Pair> Ly = new ArrayList<>();
            List<Pair> Ry = new ArrayList<>();

            int L = 0, R = 0;

            Pair pmid = px.get(mid);

            for (Pair pair : py) {
                if (pair.x < pmid.x) {
                    Ly.add(pair);
                    L++;
                } else if (pair.x > pmid.x) {
                    Ry.add(pair);
                    R++;
                }
                // py.x 와 mid.x 가 같은경우, y값 비교
                else {
                    if (pair.y <= pmid.y) {
                        Ly.add(pair);
                        L++;
                    } else {
                        Ry.add(pair);
                        R++;
                    }
                }
            }

            double a = closedpair(Lx,Ly);
            double b = closedpair(Rx,Ry);

            double n = Math.min(a,b);

            return splitclosedpair(px,py,n);
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
            //

            // list를 x값 작은 순서대로 정렬, x가 같을 시에는 y로 정렬2
            // 역순은 어떻게?
            List<Pair> px = list.stream().sorted(Comparator.comparing(Pair::getPairx).thenComparing(Pair::getPairy)).toList();
            List<Pair> py = list.stream().sorted(Comparator.comparing(Pair::getPairy).thenComparing(Pair::getPairx)).toList();

            if(px.size()==1){
                System.out.println();
            }
            else
                System.out.printf("%.2f%n",closedpair(px,py));

        }
    }
}
