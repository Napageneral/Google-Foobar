package gearing_up_for_destruction;

/**
 * Created by Tyler Brandt on 5/9/2017.
 */
public class pegs {


    public static int[] answer(int[] pegs) {

        int a = lastGear(pegs)*2;

        int b = (pegs.length%2==0) ? 3 : 1;
        if(a%b==0){
            a/=b;
            b=1;
        }


        float prevR = ((float)a)/((float)b);
        for(int i = 0; i < pegs.length - 2; i++){
            int width = pegs[i+1] - pegs[i];
            if(prevR<0 || prevR > (width-1)) return new int[] {-1,-1};
            prevR = width-prevR;
        }

        return new int[] {a,b};
    }

    private static int lastGear(int[] pegs) {
        int a = pegs[0];
        int flip = -1;
        for (int peg : pegs) {
            a += 2 * peg * flip;
            flip *= -1;
        }
        a = a + pegs[pegs.length - 1] * flip;
        return a;
    }


}
