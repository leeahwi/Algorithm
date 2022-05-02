import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {

    public static String solution(int[] numbers) {

        // int -> String 배열로
        ArrayList<String> strNumbers = new ArrayList();
        for(Integer num : numbers){
            strNumbers.add(String.valueOf(num));
        }
        // 내림차순 정렬
        Collections.sort(strNumbers, (a, b) -> (b + a).compareTo(a + b));
        // numbers가 [0,0,0] 등으로 주어지는 경우는 "000"이 아닌 "0" 리턴
        if(strNumbers.get(0).startsWith("0")) return "0";

        return strNumbers.stream().collect(Collectors.joining());
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test  = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){
            int n = Integer.parseInt(br.readLine().strip());

            int[] Nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            System.out.println(solution(Nums));

        }
    }
}
