package ion_flux_relabeling;

import java.util.TreeMap;
/**
 * Created by Tyler Brandt on 5/11/2017.
 */
public class Answer {
    public static int[] answer(int h, int[] q) {

        //xphoniex

        int size = (int)Math.pow(2,h)-1;
        int answer[] = new int[q.length];

        for (int i = 0 ; i < q.length ; i++) {
            if (q[i] < size && q[i] >=1)
                answer[i] = locate(size,q[i],size-1);
            else
                answer[i] = -1;
        }
        return answer;
    }

    public static int locate(int size, int target, int under) {

        under /= 2;
        int right = size - 1;
        int left = size - 1 - under--;

        if (right == target || left == target)
            return size;
        else {
            if (target <= left)
                return locate(left,target,under);
            else
                return locate(right,target,under);
        }
    }
}
