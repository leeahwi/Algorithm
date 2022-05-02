import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    //블록 수, y, x 순
    // 1, 2, 3, 4번 블록
    public static int[][][] blk =
            {{{0,0},{1,0},{0,1}},
            {{0,0},{0,1},{1,1}},
            {{0,0},{1,0},{1,1}},
            {{0,0},{1,0},{1,-1}}};

    public static boolean set(int[][] board,int y,int x,int type,int delta){

        boolean ok = true;

        for(int i=0;i<3;i++){

            int ny = y + blk[type][i][0];
            int nx = x + blk[type][i][1];

            if(ny<0 || ny >= board.length || nx < 0 || nx >= board[0].length){
                ok = false;
            }
            //아래 코드에서 설정함
            else if((board[ny][nx] += delta) > 1){
                ok = false;
            }

        }
        return ok;
    }

    public static int cover(int[][] board){

        int y=-1, x=-1; //첫칸이 빈칸일수도 있으니까 -1,-1로 설정

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==0){
                    y = i;
                    x = j;
                    break;
                }
            }
            if(y!=-1)
                break;
        }

        //아래는 전체칸이 위에서 설정되지 않아 y가 그대로 -1이 되었기에 전체칸이 채워졌음을 의미
        if(y==-1){
            return 1;
        }

        int ret = 0;

        //4개의 블럭으로 반복
        for(int i=0;i<4;i++){
            //칸에 넣을수 있다는 뜻
            if(set(board,y,x,i,1)){
                ret += cover(board);    //여기에서 재귀계속 일어나면서 중간에 가능성이 없으면 if문 조건문에 안들어감
            }
            //위에서 임의의 블럭을 넣었다고 설정했기에 아래에서는 이를 빼주면서 다음 블럭 넣고 재귀 -> 백트래킹
            set(board,y,x,i,-1);     //delta에 -1을 넣음으로써 기존에 +1 해주었던걸 빼줌
        }

        return ret;
    }

    //아래 두 코드는 나중에 뜯어 고치자
    public static boolean check_lee(boolean[][] board,int bnum, int x, int y){

        if(board[x][y]==false)
            return false;
        else{
            for(int i=0;i<3;i++) {
                int px = x + blk[bnum][i][1];
                int py = x + blk[bnum][i][0];

                if(board[py][px]==false){
                    return false;
                }
                else{
                    continue;
                }

            }

            return true;

        }
    }

    public static int GameBoardFunc_lee(boolean[][] board,int y, int x){

        int ny = -1;
        int nx = -1;
        int count = 0;
        //왼쪽 상단 부터 오른쪽으로 오면서 true(빈칸) 찾아서 반환
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                if(board[i][j]==true){
                    ny = i;
                    nx = j;
                    break;
                }
            }
            if(ny!=-1)
                break;
        }

        //4개의 블록 가능 여부 체크
        for(int a=0;a<4;a++){
            //a번 블록이 가능할시
            if(check_lee(board,a,nx,ny)){
                for(int i=0;i<3;i++) {
                    int px = nx + blk[a][i][1];
                    int py = ny + blk[a][i][0];
                    board[py][px] = false;
                }
                int n = GameBoardFunc_lee(board,y,x+1);
                if(n!=0)
                    count += n;

            }
            else{
                return 0;
            }

        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        //int test = Integer.parseInt(br.readLine().trim());
        int test = Integer.parseInt(br.readLine().strip());

        for(int i=0;i<test;i++){

            //int[] nums = Arrays.asList(br.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();

            //int h = nums[0];    //1<=h<=20
            //int w = nums[1];    //1<=w<=20

            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            int h = Integer.parseInt(st.nextToken());    //1<=h<=20
            int w = Integer.parseInt(st.nextToken());    //1<=w<=20


            int[][] board = new int[h][w];  //게임판 입력받을 배열

            //게임판 입력받은 문자열들을 boolean 형으로 변환해서 준비
            for(int j=0;j<h;j++){

                String a = br.readLine();

                for(int k=0;k<w;k++){

                    if(a.charAt(k) == '#'){
                        board[j][k] = 1;
                    }
                    else{
                        board[j][k] = 0;
                    }
                }

            }

            System.out.println(cover(board));

        }
    }
}
