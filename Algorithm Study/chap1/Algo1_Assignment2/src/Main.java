import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testNum = Integer.parseInt(br.readLine());

        Set<Character> set = new HashSet<>(3);

        for(int i=0;i<testNum;i++){

            String str = br.readLine();

            int count = 0;
            //생각해보자. 부분 문자열은 (str 문자 갯수)-2 나온다. 저걸 n이라 따졌을때 n-2 (n<=100) 최대 98개
            //테스트 횟수 n이면 n(n-2) 가 빅오인데...
            for(int j=0;j<str.length()-2;j++){

                set.clear();
//                set.addAll(Arrays.stream(str.substring(j,j+3).split("")).toList());
//                if(set.size()==3){
//                    count++;
//                }

                set.add(str.charAt(j));
                set.add(str.charAt(j+1));
                set.add(str.charAt(j+2));
                if(set.size()==3){
                    count++;
                }
                if(str.charAt(j)!=str.charAt(j+1) && str.charAt(j)!=str.charAt(j+2) && str.charAt(j+1)!=str.charAt(j+2)){
                    count++;
                }
            }

            System.out.println(count);

        }


    }
}
