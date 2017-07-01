package com.google.challenges;
/*Bomb, Baby!
===========

You're so close to destroying the LAMBCHOP doomsday device you can taste it! But in order to do so, you need to deploy special self-replicating bombs designed for you by the brightest scientists on Bunny Planet. There are two types: Mach bombs (M) and Facula bombs (F). The bombs, once released into the LAMBCHOP's inner workings, will automatically deploy to all the strategic points you've identified and destroy them at the same time.

But there's a few catches. First, the bombs self-replicate via one of two distinct processes:
Every Mach bomb retrieves a sync unit from a Facula bomb; for every Mach bomb, a Facula bomb is created;
Every Facula bomb spontaneously creates a Mach bomb.

For example, if you had 3 Mach bombs and 2 Facula bombs, they could either produce 3 Mach bombs and 5 Facula bombs, or 5 Mach bombs and 2 Facula bombs. The replication process can be changed each cycle.

Second, you need to ensure that you have exactly the right number of Mach and Facula bombs to destroy the LAMBCHOP device. Too few, and the device might survive. Too many, and you might overload the mass capacitors and create a singularity at the heart of the space station - not good!

And finally, you were only able to smuggle one of each type of bomb - one Mach, one Facula - aboard the ship when you arrived, so that's all you have to start with. (Thus it may be impossible to deploy the bombs to destroy the LAMBCHOP, but that's not going to stop you from trying!)

You need to know how many replication cycles (generations) it will take to generate the correct amount of bombs to destroy the LAMBCHOP. Write a function answer(M, F) where M and F are the number of Mach and Facula bombs needed. Return the fewest number of generations (as a string) that need to pass before you'll have the exact number of bombs necessary to destroy the LAMBCHOP, or the string "impossible" if this can't be done! M and F will be string representations of positive integers no larger than 10^50. For example, if M = "2" and F = "1", one generation would need to pass, so the answer would be "1". However, if M = "2" and F = "4", it would not be possible.

Languages
=========

To provide a Python solution, edit solution.py
To provide a Java solution, edit solution.java

Test cases
==========

Inputs:
    (string) M = "2"
    (string) F = "1"
Output:
    (string) "1"

Inputs:
    (string) M = "4"
    (string) F = "7"
Output:
    (string) "4"*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Brandt on 6/8/2017.
 */
public class bomb_baby {
    public static String answer(String M, String F) {

        String output = "impossible";
        String count = "0";

        if (M.equals("1") || F.equals("1")) return M.equals("1") ? sub(F, "1") : sub(M, "1");


        if (!checkStringValid(M, F)) return output;




        while(!(add(M,F).equals("3"))){
            if (M.length() < F.length()) {
                String temp = M;
                M = F;
                F = temp;
            }
            if (M.length() == F.length()) {
                int i = 0;
                while (M.charAt(i) == F.charAt(i) && i < M.length() - 1) {
                    i++;
                }
                if (M.charAt(i) < F.charAt(i)) {
                    String temp = M;
                    M = F;
                    F = temp;
                }
            }
            String multiplier = div(M,F);
            count = add(count,multiplier);
            M=sub(M,mult(multiplier,F));
        }
        count = add(count, "1");
        return count;


    }

        //while(M.)




            /*try {
                long numM = Long.parseLong(M);
                long numF = Long.parseLong(F);

                long trivialM = numM-1;
                long trivialF = numF-1;

                if(numM == 1 || numF ==1) return numM==1 ? ""+trivialF : ""+trivialM;

                String output = "impossible";
                long count = 0;
                while (numM > 1 || numF > 1) {

                    if (!checkIsValid(numM, numF)) {
                        numM=numF=0;
                    } else if(numM==1||numF==1){
                        count+= numM==1 ? numF-1 : numM-1;
                        numM=numF=1;
                    }else if (numM > numF) {
                        long difference = numM - numF;
                        long multiplier = (difference / numF) + 1;
                        count += multiplier;
                        numM = numM - multiplier * numF;
                    } else if (numF > numM) {
                        long difference = numF - numM;
                        long multiplier = (difference / numM) + 1;
                        count += multiplier;
                        numF = numF - multiplier * numM;
                    }

                    //System.out.println("cycle: " + count + " " + numM + " " + numF);
                }

                if (numM == 1 && numF == 1) {
                    output = "" + count;
                }

                return output;
            }
            catch (NumberFormatException nfe){
                return "impossible";
            }*/
        //}

        protected static boolean checkStringValid(String M, String F) {
            boolean valid = true;

            if ((M.charAt(M.length()-1)=='0' || M.charAt(M.length()-1)=='2' || M.charAt(M.length()-1)=='4' || M.charAt(M.length()-1)=='6' || M.charAt(M.length()-1)=='8') && (F.charAt(F.length()-1)=='0' || F.charAt(F.length()-1)=='2' || F.charAt(F.length()-1)=='4' || F.charAt(F.length()-1)=='6' || F.charAt(F.length()-1)=='8')) {
                valid = false;
            }
            else if (M.equals(F)) {
                valid = false;
            }
            else if (M.equals("0")||F.equals("0")) {
                valid = false;
            }
            //check for remainder if you can

            return valid;
        }



    //string addition
    public static String add(String a, String b) {
        while(a.charAt(0)=='0'&&a.length()>1){
            a=a.substring(1,a.length());
        }
        StringBuilder sb = new StringBuilder();

        if(a.length()<b.length()){
            String temp = a;
            a=b;
            b=temp;
        }
        int i=a.length()-1;
        int j=b.length()-1;
        int carry = 0;

        while(j>=0){
            int top = Integer.parseInt(String.valueOf(a.charAt(i)));
            int bot = Integer.parseInt(String.valueOf(b.charAt(j)));
            int sum=top+bot+carry;

            String temp = String.valueOf(sum);

            if(temp.length()>1){
                carry = 1;
                sb.insert(0, temp.charAt(1));
            } else {
                carry=0;
                sb.insert(0,temp);
            }
            i--;
            j--;
        }

        if(i>-1){
            if(carry==1){
                sb.insert(0,add(a.substring(0,i+1), "1"));
                carry=0;
            } else {
                sb.insert(0, a.substring(0, i + 1));
            }
        }
        if(carry==1)
            sb.insert(0, '1');

        return sb.toString();
    }

    public static String sub(String a, String b) {
        StringBuilder sb = new StringBuilder();
        while(a.charAt(0)=='0'&&a.length()>1){
            a=a.substring(1,a.length());
        }


        boolean neg=false;

        if(a.length()<b.length()){
            String temp = a;
            a=b;
            b=temp;
            neg = true;
        }
        if(a.length()==b.length()){
            int i=0;
            while(a.charAt(i)==b.charAt(i) && i<a.length()-1){
                i++;
            }
            if(a.charAt(i)==b.charAt(i)) return "";
            if(a.charAt(i)<b.charAt(i)){
                String temp = a;
                a=b;
                b=temp;
                neg = true;
            }
        }

        int i=a.length()-1;
        int j=b.length()-1;


        int carry = 0;

        while(j>=0){
            boolean tneg=false;
            int top = Integer.parseInt(String.valueOf(a.charAt(i)));
            int bot = Integer.parseInt(String.valueOf(b.charAt(j)));
            if(bot>top){
                top = top+10;
                tneg=true;
            }
            int dif=top-bot-carry;
            carry=0;
            if(tneg) carry=1;

            String temp = String.valueOf(dif);


            sb.insert(0, temp);

            i--;
            j--;
        }

        if(i>-1){
            if(carry==1){
                sb.insert(0,sub(a.substring(0,i+1), "1"));
            }else {
                sb.insert(0, a.substring(0, i + 1));
            }
        }

        if(neg) return "-"+sb.toString();

        return sb.toString();
    }

    public static String div(String a, String b){
        if(b.equals("1")) return ""+a;
        int count = 0;

        while(!(a.charAt(0) =='-')){
            a=sub(a,b);
            count++;
            if(a.equals("")) a="0";
        }
        count--;
        return ""+count;
    }

    public static String mult(String a, String b){
        String plus = a;
        while(!(b.equals("1"))){
            a=add(a,plus);
            b=sub(b,"1");
        }
        return ""+a;
    }
    public static void main(String[] args) {

        //System.out.println(answer("1234567890123456789012345678901234567890123456789","1"));
        String a = "12345678901234567890123456789012345678901234567890";
        String b = "3";
        //System.out.println(add(a,b));
        //System.out.println(sub(a,b));
        //System.out.println(sub(b,a));
        //System.out.println(div(b,a));
        //System.out.println(div(a,b));
        //System.out.println(mult(b,a));
       //System.out.println(mult(a,b));

        System.out.println(answer(a,b));



        /*int size = 16;
        List<IntPair>[] group;
        group = new List[size+1];


        for(int curBin = 0; curBin<=size; curBin++){
            group[curBin] = new ArrayList<>();
        }

        group[0].add(new IntPair(1,1));



        for(int i =1; i<=size; i++){
            for (IntPair pair:group[i-1]){
                group[i].add(new IntPair(pair.getX()+pair.getY(), pair.getY()));
                group[i].add(new IntPair(pair.getX(), pair.getX()+pair.getY()));
            }
        }

        for(int i =0; i<size; i++){
            System.out.print("Gen "+i+": ");
            for (IntPair pair:group[i]){
                //System.out.print(pair.sum()+" ");
                System.out.print(pair.toString()+" ");
            }
            System.out.println();
            System.out.flush();
        }*/
    }

    static class IntPair {
        // Ideally, name the class after whatever you're actually using
        // the int pairs *for.*
        int x = 0;
        int y = 0;
        IntPair(int x, int y) {this.x=x;this.y=y;}

        int getX(){
            return this.x;
        }
        int getY(){
            return this.y;
        }
        int sum(){
            return x+y;
        }


        public String toString(){
            return "("+getX()+","+getY()+")";
        }
        // depending on your use case, equals? hashCode?  More methods?
    }

    int fib(int n){
        return fib(n, new int[n+1]);
    }
    int fib(int i, int[] memo){
        if(i==0||i==1) return i;

        if(memo[i] == 0){
            memo[i] = fib(i-1, memo) + fib(i-2, memo);
        }
        return memo[i];
    }
}

//Semi-working
/*public static String answer(String M, String F) {
            try{
                long numM = Long.parseLong(M);
                long numF = Long.parseLong(F);

                long trivialM = numM-1;
                long trivialF = numF-1;

                if(numM == 1 || numF ==1) return numM==1 ? ""+trivialF : ""+trivialM;

                String output = "impossible";
                long count = 0;
                while (numM > 1 || numF > 1) {

                    if (!checkIsValid(numM, numF)) {
                        numM=numF=0;
                    }
                    else if(numM==1||numF==1){
                        count+= numM==1 ? numF-1 : numM-1;
                        numM=numF=1;
                    }
                    else if (numM > numF) {
                        long difference = numM - numF;
                        long multiplier = (difference / numF) + 1;
                        count += multiplier;
                        numM = numM - multiplier*numF;
                    }
                    else if (numF > numM) {
                        long difference = numF - numM;
                        long multiplier = (difference / numM) + 1;
                        count += multiplier;
                        numF = numF - multiplier*numM;
                    }

                    //System.out.println("cycle: " + count + " " + numM + " " + numF);
                }

                if (numM == 1 && numF == 1) {
                    output = ""+count;
                }

                return output;
            }
            catch (NumberFormatException nfe) {
                    return "impossible";
            }

        }

        protected static boolean checkIsValid(long numM, long numF) {
            boolean valid = true;
            if (numM % 2 == 0 && numF % 2 == 0) {
                valid = false;
            }
            else if (numM == numF) {
                valid = false;
            }
            else if (numM <= 0 || numF <= 0) {
                valid = false;
            }
            else if (numM > 1 && numF % numM == 0) {
                valid = false;
            }
            else if (numF > 1 && numM % numF == 0) {
                valid = false;
            }

            return valid;
    }*/

