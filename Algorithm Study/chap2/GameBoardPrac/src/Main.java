import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] board = null;

    static int[][][] blk = {
        {{0,0},{0,1},{1,1}},    //ㄱ
        {{0,0},{1,0},{1,1}},    //ㄴ
        {{0,0},{1,0},{1,-1}},    //좌우반전 ㄴ
        {{0,0},{1,0},{0,1}}     //좌우반전 ㄱ
    };

    //체크하고 바꾸는 set 함수
    //cover 함수 -> 재귀할 함수

    //(y,x) 에서 type번 블록이 가능하면 true 안될시에 false
    public static boolean set(int y,int x, int type,int delet){

        boolean ok = true;

        for(int i=0;i<blk[type].length;i++){

            int nx = x + blk[type][i][1];
            int ny = y + blk[type][i][0];

            if(nx < 0 || nx >= board[0].length || ny < 0 || ny>= board.length){
                ok = false;
            }
            else if((board[ny][nx]+=delet)>=2){
                ok = false;
            }
        }

        return ok;
    }

    public static int cover(int[][] board){

        int y = -1;
        int x = -1;
        int result = 0;

        for(int j=0;j<board.length;j++){
            for(int k=0;k<board[j].length;k++){

                // (y,x) -> (j,k) 자리 비었을때
                if(board[j][k]==0){
                    y = j;
                    x = k;
                    break;
                }
            }
            if(y!=-1){
                break;
            }
        }

        if(y==-1) {
            return 1;
        }

        for(int i=0;i<4;i++){
            if(set(y,x,i,1)){
                result += cover(board);
                set(y,x,i,-1);
            }

        }

        return result;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            board = new int[h][w];

            for(int j=0;j<board.length;j++){

                String s = br.readLine();

                for(int k=0;k<board[j].length;k++){

                    if(s.charAt(k)=='#'){
                        board[j][k] = 1;
                    }
                    else{
                        board[j][k] = 0;
                    }
                }
            }

            //인수로 board를 넘겨주는 이유는 2차 배열 이라서?
            System.out.println(cover(board));

        }

    }
}
