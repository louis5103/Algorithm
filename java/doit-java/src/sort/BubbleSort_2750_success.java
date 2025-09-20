package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class mData implements Comparable<mData>{
    int value;
    int index;
    public mData(int value, int index) {
        this.value = value;
        this.index = index;
    }
    @Override
    public int compareTo(mData other){
        return this.value - other.value;
    }
    public void main() throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(rd.readLine());

        mData[] arr = new mData[num];
        for(int i=0; i<num; i++){
            arr[i] = new mData(Integer.parseInt(rd.readLine()), i);
        }
        Arrays.sort(arr);
        int max = 0;
        for(int i=0; i<num; i++){
            if (max < arr[i].index - i)
                max = arr[i].index - i;
        }
        System.out.println(max+1);
    }

    public class BubbleSort_2750_success {
    }
}

