
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //테스트 횟수 받는 코드
        int testNum = Integer.parseInt(br.readLine());

        for(int i=0;i<testNum;i++){

            //해당 테스트의 카드의 갯수 입력
            int cardNum = Integer.parseInt(br.readLine());

            //카드 갯수 만큼의 크기의 Set 준비
            Set<String> set = new HashSet<>();

            //각 해당 카드들을 한 줄로 입력받음
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = st.countTokens();
            //변환 없이 그대로 넣기
            for(int j=0;j<num;j++){
                set.add(st.nextToken());
            }

            //단순히 중복여부 확인이라면 각 카드 값 전부다 Set에 넣은뒤 Set의 크기와 넣기전 토큰들 갯수와 다른지 체크
            if(set.size()<cardNum){
                System.out.println("true");
            }
            else{
                System.out.println("false");
            }


        }

    }
}