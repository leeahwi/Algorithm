
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void mergeSort(int[] array, int lo, int hi){
        if(hi-lo>=2) {
            int mid = lo + (hi - lo) / 2;
            mergeSort(array, lo, mid);
            mergeSort(array, mid + 1, hi);
            merge(array, lo, mid, hi);
        }
    }

    public static void merge(int[] array,int lo, int mid,int hi){
        int[] temp = new int[hi - lo];
        int t = 0, l = lo, h = mid;

        while (l < mid && h < hi) {
            if (array[l] < array[h]) {
                temp[t++] = array[l++];
            } else {
                temp[t++] = array[h++];
            }
        }

        while (l < mid) {
            temp[t++] = array[l++];
        }

        while (h < hi) {
            temp[t++] = array[h++];
        }

        for (int i = lo; i < hi; i++) {
            array[i] = temp[i - lo];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testNum = Integer.parseInt(br.readLine());

        for(int i=0;i<testNum;i++){

            int num = Integer.parseInt(br.readLine());

            String[] str = br.readLine().split(" ");

            int[] list = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();

            mergeSort(list,0,list.length);

            for(int a:list){
                System.out.print(a);
                System.out.print(" ");
            }

        }

    }
}
