package com.google.challenges;

import java.math.BigInteger;

/**
 * Created by Tyler Brandt on 6/11/2017.
 */
public class bomb_baby_BI {

    public static String answer(String M, String F) {

        BigInteger two = new BigInteger("2");
        BigInteger one = new BigInteger("1");
        BigInteger zero = new BigInteger("0");

        BigInteger counter = zero;
        BigInteger mNum = new BigInteger(M);
        BigInteger fNum = new BigInteger(F);
        if (!checkBIValid(mNum, fNum)) return "impossible";
        if (mNum.equals(one) || fNum.equals(one))
            return mNum.equals(one) ? fNum.subtract(one).toString() : mNum.subtract(one).toString();



        while (!(mNum.add(fNum).equals(new BigInteger("3")))) {

            BigInteger max = mNum.max(fNum);
            BigInteger min = mNum.min(fNum);
            BigInteger change = max.divide(min);
            counter = counter.add(change);
            if(mNum.max(fNum).equals(mNum)){
                mNum = mNum.subtract(fNum.multiply(change));
            } else {
                fNum = fNum.subtract(mNum.multiply(change));
            }
            if(mNum.equals(one)||fNum.equals(one)){
                counter = counter.add(mNum.equals(one) ? fNum.subtract(one): mNum.subtract(one));
                counter = counter.subtract(one);
                mNum = two;
                fNum = one;
            }
        }
        counter = counter.add(one);

        return counter.toString();
    }

    protected static boolean checkBIValid(BigInteger M, BigInteger F) {
        boolean valid = true;

        if (M.remainder(new BigInteger("2")).intValue()==0 && F.remainder(new BigInteger("2")).intValue()==0 ) {
            valid = false;
        }
        else if (M.equals(F)) {
            valid = false;
        }
        else if (M.equals(new BigInteger("0"))||F.equals(new BigInteger("0"))) {
            valid = false;
        }
        //check for remainder if you can

        return valid;
    }

    public static void main(String[] args) {
        String M = "1235465489";
        String F = "5";
        System.out.println(answer(M,F));
    }

}
