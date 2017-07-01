package com.google.challenges;

import java.math.BigInteger;

/**
 * Created by Tyler Brandt on 6/11/2017.
 */
public class bomb_baby_long {

    static long[] zero = toLong("0");
    static long[] one = toLong("1");

    public static String answer(String M, String F) {

        try {
            long[] mSet = toLong(M);
            long[] fSet = toLong(F);



            long[] counter = toLong("0");

            if (!checkLongValid(mSet, fSet)) return "impossible";

            while (!(mSet[0] + fSet[0] == 3)) {
                if (longGr(fSet,mSet)) {
                    long[] temp = mSet;
                    mSet = fSet;
                    fSet = temp;
                }
                long[] change = longDiv(mSet,fSet);
                counter = longAdd(counter,change);
                mSet = longSub(mSet,longMult(fSet,change));

                if (mSet[0] == 1 || fSet[0] == 1) {
                    if (mSet[0] == 1) counter = longAdd(counter,longSub(fSet,one));
                    else {
                        counter = longAdd(counter,longSub(mSet,one));
                    }
                    counter = longSub(counter,one);
                    mSet[0] = 2;
                    fSet[0] = 1;
                }
            }
            counter = longAdd(counter,one);

            return longV(counter);
        }catch (NumberFormatException nfe) {
            return "impossible";
        }
    }



    protected static boolean checkLongValid(long[] M, long[] F) {
        boolean valid = true;

        if (M[0]%2==0 && F[0]%2==0 ) {
            valid = false;
        }
        else if (isEqual(M,F)) {
            valid = false;
        }
        else if (isEmpty(M)||isEmpty(F)) {
            valid = false;
        }
        else if (longGr(M,F) && M[0]%F[0]==0){
            valid = false;
        }
        else if (F[0]%M[0]==0){
            valid = false;
        }

        return valid;
    }

    static long[] longAdd(long[] a, long[] b){

        if (isEmpty(b)) return a;
        if (isEmpty(a)) return b;

        if (longGr(b,a)) {
            long[] temp = a;
            a = b;
            b = temp;
        }

        StringBuilder result = new StringBuilder();
        int i = 0;
        long carry=0;
        while(!(b[i]==0)){
            long temp = a[i]+b[i]+carry;
            String over = ""+temp;
            if(over.length()>9){
                carry = Long.parseLong(over.substring(0,over.length()-9));
                over = over.substring(over.length()-9,over.length());
            }
            if(over.length()<9){
                over=pad(over);
            }
            result.insert(0,over);
            i++;
        }

        String sum = result.toString();
        //while (sum.charAt(0)=='0' && sum.length()>1){
        //    sum = sum.substring(1,sum.length());
        //}
        return toLong(sum);
    }

    static long[] longSub(long[] a, long[] b){
        //boolean neg = false;
        if (isEmpty(b)) return a;
        if (isEqual(a,b)) return zero;
        if (longGr(b,a)) {
            long[] temp = a;
            a = b;
            b = temp;
            //neg = true;
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        long carry=0;
        while(!(b[i]==0)){
            long temp = a[i]-b[i]-carry;
            carry=0;
            long unit = 1000000000;
            String over = ""+temp;
            if (temp<0){
                carry = 1;
                over = ""+(-temp);
            }
            if (over.length()<9){
                over=pad(over);
            }
            result.insert(0,over);
            i++;
        }
        String dif = result.toString();
        //if (neg) dif = "-"+dif;
        //while (dif.charAt(0)=='0' && dif.length()>1){
        //    dif = dif.substring(1,dif.length());
        //}
        return toLong(dif);
    }

    static long[] longMult(long[] a, long[] b){
        if (isEmpty(b)) return zero;
        StringBuilder result = new StringBuilder();
        long carry=0;
        int i = 0;
        while(!(b[i]==0)){
            for (int p = 0;p<=maxP(a);p++){
                String over = ""+(a[p]+carry)*b[i];
                carry=0;
                if(over.length()>9){
                    carry = Long.parseLong(over.substring(0,over.length()-9));
                    over = over.substring(over.length()-9,over.length());
                }
                if(over.length()<9){
                    over=pad(over);
                }
                result.insert(0,over);
            }
            i++;
        }
        String ret = result.toString();
        //while (ret.charAt(0)=='0' && ret.length()>1){
        //    ret = ret.substring(1,ret.length());
        //}
        return toLong(ret);
    }

    static long[] longDiv(long[] a, long[] b){
        if (isEqual(a,b)) return one;
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(!(b[i]==0)){
            for (int j = maxP(a);j>=0;j--){
                long temp = a[j] / b[i];
                String over = ""+temp;
                if (over.length() < 9) {
                    over = pad(over);
                }
                result.insert(0, over);
            }
            i++;
        }
        String ret = result.toString();
        //while (ret.charAt(0)=='0' && ret.length()>1){
        //    ret = ret.substring(1,ret.length());
        //}
        return toLong(ret);
    }

    static String longV(long[] a){
        StringBuilder sb = new StringBuilder();
        if (isEmpty(a)) return "0";

        for(int i = 0;i<=maxP(a);i++){
            sb.insert(0,a[i]);
        }
        String ret = sb.toString();
        //while (ret.charAt(0)=='0' && ret.length()>1){
        //    ret = ret.substring(1,ret.length());
        //}
        return ret;
    }

    static boolean longGr(long[] M, long[] F){
        if (isEmpty(F)) return true;
        if (isEqual(M,F)) return false;
        int a = maxP(M);
        int b = maxP(F);
        if (a==b){
            for (int i =a; i>=0; i--)
                if (M[a]>F[b]) return true;
        }
        return (a > b);
    }


    static boolean isEmpty(long[] M){
        return M[0] == 0 && M[1] == 0 && M[2] == 0 && M[3] == 0 && M[4] == 0 && M[5] == 0;
    }

    static boolean isEqual(long[] M, long[] F){
        return M[0]==F[0] && M[1]==F[1] && M[2]==F[2] && M[3]==F[3] && M[4]==F[4] && M[5]==F[5];
    }

    static int maxP(long[] a){
        int count = 5;
        while(a[count]==0){
            count--;
        }
        return count;
    }


    static String pad(String over){
        int place = 9-over.length();
        switch (place){
            case(1):
                 over="0"+over;
                break;
            case(2):
                 over="00"+over;
                break;
            case(3):
                over="000"+over;
                break;
            case(4):
                 over="0000"+over;
                break;
            case(5):
                 over="00000"+over;
                break;
            case(6):
                over="000000"+over;
                break;
            case(7):
                 over="000000"+over;
                break;
            case(8):
                over="0000000"+over;
                break;
        }
        return over;
    }

    static long[] toLong(String M){
        long[] mSet = new long[6];


        boolean Mset = false;

        int mLen = M.length();

        if ((mLen>9)) {
            Mset = true;
        } else{
            mSet[0] = Long.parseLong(M);
        }

        if(Mset){
            if (mLen>45){

                mSet[0] = Long.parseLong(M.substring(45, mLen));
                mSet[1] = Long.parseLong(M.substring(36, 45));
                mSet[2] = Long.parseLong(M.substring(27, 36));
                mSet[3] = Long.parseLong(M.substring(18, 27));
                mSet[4] = Long.parseLong(M.substring(9, 18));
                mSet[5] = Long.parseLong(M.substring(0, 9));
            } else if ( mLen > 36){

                mSet[0] = Long.parseLong(M.substring(36, mLen));
                mSet[1] = Long.parseLong(M.substring(27, 36));
                mSet[2] = Long.parseLong(M.substring(18, 27));
                mSet[3] = Long.parseLong(M.substring(9, 18));
                mSet[4] = Long.parseLong(M.substring(0, 9));
            } else if(mLen>27){

                mSet[0] = Long.parseLong(M.substring(27, mLen));
                mSet[1] = Long.parseLong(M.substring(18, 27));
                mSet[2] = Long.parseLong(M.substring(9, 18));
                mSet[3] = Long.parseLong(M.substring(0, 9));
            } else if (mLen>18){

                mSet[0] = Long.parseLong(M.substring(18, mLen));
                mSet[1] = Long.parseLong(M.substring(9, 18));
                mSet[2] = Long.parseLong(M.substring(0, 9));
            } else {

                mSet[0] = Long.parseLong(M.substring(9, mLen));
                mSet[1] = Long.parseLong(M.substring(0, 9));
            }
        }
        return mSet;
    }

    public static void main(String[] args) {
        String M = "1235465489";
        String F = "5";
        System.out.println(answer(M,F));
    }



}






    /*protected static boolean checkLongValid(long M, long F) {
        boolean valid = true;

        if (M%2==0 && F%2==0 ) {
            valid = false;
        }
        else if (M==F) {
            valid = false;
        }
        else if (M==0||F==0) {
            valid = false;
        }
        else if (M>F && M%F==0){
            valid = false;
        }
        else if (F>M && F%M==0){
            valid = false;
        }

        return valid;
    }*/