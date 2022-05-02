import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    //짧은거 우선, 길이가 같으면 숫자 큰거로
    public static class Num implements Comparable<Num>{
        String value;
        int count = 0;
        public Num(String s){
            this.value = s;
        }
        @Override
        public int compareTo(Num o){

            if(this.value.length()==o.value.length()){
                return Integer.compare(Integer.parseInt(o.value),Integer.parseInt(this.value));
            }
            else{
                return Integer.compare(this.value.length(),o.value.length());
            }
        }

    }
    public static void solve(List<ArrayList<Num>> list){

        String[] result = new String[10];

        int k=0;
        for(int i=9;i>=0;i--){
            result[k++] = list.get(i).stream().map(x-> x.value).collect(Collectors.joining());
        }

        if(Integer.parseInt(String.join("", result))==0)
            System.out.println(0);
        else
            System.out.println(String.join("", result));

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test  = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){
            int n = Integer.parseInt(br.readLine().strip());

            String[] Nums = br.readLine().split(" ");

            List<ArrayList<Num>> list = new ArrayList<>();

            for(int k=0;k<=9;k++){
                list.add(new ArrayList<Num>());
            }

            for(String num: Nums){
                int index = (int)num.charAt(0)-48;

                ArrayList<Num> nNums = list.get(index);

                nNums.add(new Num(num));

            }

            for(ArrayList<Num> list1 : list){
                list1.sort(Num::compareTo);
            }

            solve(list);

        }
    }
}
