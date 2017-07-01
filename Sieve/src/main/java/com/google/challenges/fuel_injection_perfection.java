package com.google.challenges;

/*Fuel Injection Perfection
        =========================

        Commander Lambda has asked for your help to refine the automatic quantum antimatter fuel injection system for her LAMBCHOP doomsday device. It's a great chance for you to get a closer look at the LAMBCHOP - and maybe sneak in a bit of sabotage while you're at it - so you took the job gladly.

        Quantum antimatter fuel comes in small pellets, which is convenient since the many moving parts of the LAMBCHOP each need to be fed fuel one pellet at a time. However, minions dump pellets in bulk into the fuel intake. You need to figure out the most efficient way to sort and shift the pellets down to a single pellet at a time.

        The fuel control mechanisms have three operations:

        1) Add one fuel pellet
        2) Remove one fuel pellet
        3) Divide the entire group of fuel pellets by 2 (due to the destructive energy released when a quantum antimatter pellet is cut in half, the safety controls will only allow this to happen if there is an even number of pellets)

        Write a function called answer(n) which takes a positive integer as a string and returns the minimum number of operations needed to transform the number of pellets to 1. The fuel intake control panel can only display a number up to 309 digits long, so there won't ever be more pellets than you can express in that many digits.

        For example:
        answer(4) returns 2: 4 -> 2 -> 1
        answer(15) returns 5: 15 -> 16 -> 8 -> 4 -> 2 -> 1


        Languages
        =========

        To provide a Python solution, edit solution.py
        To provide a Java solution, edit solution.java

        Test cases
        ==========

        Inputs:
        (string) n = "4"
        Output:
        (int) 2

        Inputs:
        (string) n = "15"
        Output:
        (int) 5
*/
/**
 * Created by Tyler Brandt on 6/7/2017.
 */
public class fuel_injection_perfection {

    public static void main(String[] args) {

        System.out.println(answer(args[0]));

    }

        public static int answer(String n) {

            if(n.equals("0") || n.equals("1")) return 0;

            //convert n to bin
            String binNum = dectobin(n);
            int len = binNum.length()-1;

            int count =0;
            while(len>0 && !binNum.equals("1")) {

                if (binNum.charAt(len) == '0') {

                    count++;
                    binNum = binNum.substring(0,binNum.length()-1);

                } else if (len > 1 && binNum.charAt(len-1) == '1' && binNum.charAt(len) == '1') {

                    binNum = binAdd(binNum,"1");
                    count++;
                    len = binNum.length();

                } else {

                    binNum = binNum.substring(0,binNum.length()-1);

                    count++;
                    count++;
                }

                len--;
            }

            /*
            //add # of 1's minus 1 to count
            int count=0;
            for(int i = len; i>=0; i--){
                if(binNum.charAt(i)=='1') count++;
            }
            if(count ==0) return 0;
            count--;

            //count powers of 2 in n
            count=count+len;
            */

            return count;

        }

        public static String bconvert(char x){
            if(x=='1') return "1";
            if(x=='2') return "10";
            if(x=='3') return "11";
            if(x=='4') return "100";
            if(x=='5') return "101";
            if(x=='6') return "110";
            if(x=='7') return "111";
            if(x=='8') return "1000";
            if(x=='9') return "1001";
            else return "0";
        }


        public static String binAdd(String a, String b) {
            StringBuilder sb = new StringBuilder();

            int i=a.length()-1;
            int j=b.length()-1;

            int carry = 0;

            while(i>=0 || j>=0){
                int sum=0;

                if(i>=0 && a.charAt(i)=='1'){
                    sum++;
                }

                if(j>=0 && b.charAt(j)=='1'){
                    sum++;
                }

                sum += carry;

                if(sum>=2){
                    carry=1;
                }else{
                    carry=0;
                }

                sb.insert(0,  (char) ((sum%2) + '0'));

                i--;
                j--;
            }

            if(carry==1)
                sb.insert(0, '1');

            return sb.toString();
        }




        public static String binMult(String a, String b){
            if(a.equals("0") || b.equals("0")){
                return "0";
            }

            if(a.length()<b.length()){
                String temp = a;
                a=b;
                b=temp;
            }

            String result= "";
            String unit ="";
            int bLen = b.length()-1;

            while(bLen>=0){
                if(b.charAt(bLen)=='1') result = binAdd(result, a+unit);
                unit += "0";
                bLen--;
            }
            return result;
        }

        public static String dectobin(String n){

            int nLen = n.length()-1;
            String result = "";
            String unit= "1";

            while(nLen>=0){
                result = binAdd(result, binMult(bconvert(n.charAt(nLen)),unit));
                nLen--;
                unit = binMult(unit,"1010");
            }
            return result;
        }

        /*public static String binAdd(String a, String b){
            if(a.length()<b.length()){
                String temp = a;
                a=b;
                b=temp;
            }

            String result = "";
            int aLen = a.length()-1;
            int bLen = b.length()-1;
            int carry = 0;

            while(bLen >=0){
                if(a.charAt(aLen)=='0' && b.charAt(bLen)=='0'){
                    if(carry==0) result = '0'+result;
                    else{
                        result = '1'+result;
                        carry=0;
                    }
                } else if(a.charAt(aLen)=='1' ^ b.charAt(bLen)=='1'){
                    if(carry==0) result = '1' +result;
                    else{
                        result = '0'+result;
                    }
                } else if(a.charAt(aLen)=='1' && b.charAt(bLen)=='1'){
                    if(carry==0){
                        result = '0' + result;
                        carry=1;
                    }else{
                        result = '1' + result;
                    }
                }
                bLen--;
                aLen--;
            }

            if(carry==0 && aLen>=0) return (a.substring(0,aLen+1)+result);

            while(aLen>=0){
                if(carry==0){
                    result = a.charAt(aLen)+result;
                } else {
                    if(a.charAt(aLen)=='1'){
                        result = '0'+result;
                    } else{
                        result = '1'+result;
                        carry = 0;
                    }
                }
                aLen--;
            }
            if(carry==1) result = '1'+result;
            return result;
        }
        */

        /*
        //convert n to bin
        BigInteger num = new BigInteger(n);
            if(n == "1") return 0;

        //count (a1) powers of 2 in n by bitshifting until only 1 in last digit
        int a1=0;
        BigInteger temp = new BigInteger(n);
            while(!(temp==1)){
        temp>>>=1;
        a1++;
        }
        //remove the leading 1 from binary n and count (a2) 1's remaining
        temp = clearBit(num, a1);
        int a2 = countBits(temp);

        //answer = a1 plus a2
            return a1+a2;

        static int countBits(BigInteger num){
            int count = 0;
            while(!(num==0)){
                count =+ num&1;
                num>>=1;
            }
            return count;
        }

        static BigInteger clearBit(BigInteger num, int i){
            BigInteger mask = ~(1<<i);
            return num & mask;
        }
        */

}


