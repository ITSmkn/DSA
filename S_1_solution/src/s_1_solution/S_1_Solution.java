package s_1_solution;
 
import java.util.ArrayList;


public class S_1_Solution {
    
    
    // ================= functions ==================
    
    //    ====================== # Q1 #  
    //    ================== returns the a/b ===========
    
    public static int kharej_ghesmat(int a , int b){ 
        
        if( b == 0 ){
            System.out.println("Numbers are not divisible by 0! try again!");
            return 0;
        }
        
        if(b >= a){
            return ((b==a) ? 1 : 0);
        }
        
        else{
            return 1 + kharej_ghesmat(a - b, b);
        }
        
    }
    
    //    ====================== # Q2 #   
    //    ================= returns the average of an array =========
    
    public static int avg(int array [] , int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return array[0]/array.length;
        }
        else{
            array[n - 2] = array[n - 2] + array[n - 1];
            return avg(array , n - 1);
        }
        
    }
    
    //    ====================== # Q3 #   
    //    ====================== reverse ====================

    public static int[] reverse(int array[], int n) {

        int i = array.length - n;
        if (n == 0) {
            System.out.println("invalid!");
            return null;
        }
        if (n == 1) {
            return array;
        }
        if (n == 2 || n == 3) {
            int c = array[n - 1];
            array[n - 1] = array[i];
            array[i] = c;
            return array;
        } 
        else {
            int temp = array[n - 1];
            array[n - 1] = array[i];
            array[i] = temp;

            reverse(array, n - 1);

            return array;
        }
    }
    
    //    ====================== # Q4 #   
    //    =========  converts a number to binary version ...

    public static int binary(int a) {
        if (a == 0) {
            return 0;
        }
        if (a == 1) {
            return 1;
        } else {
            boolean sign = true;
            int i = 1;
            while (sign) {
                if (Math.pow(2, i + 1) <= a) {
                    ++i;
                } else {
                    sign = false;
                }

            }
            int bi = (int) Math.pow(2, i);
            int bi10 = (int) Math.pow(10, i);
            a = a - bi;

            return bi10 + binary(a);
        }

    }
    
//    ===================== Q5 ====================
//    ============= returns the max of an array ===
    public static int maximum(int array[], int n) {
        if (n == 1) {
            return array[0];
        }
        if (n == 2) {
            return Math.max(array[0], array[1]);
        } else {
            if (array[n - 1] > array[n - 2]) {
                int t = array[n - 1];
                array[n - 1] = array[n - 2];
                array[n - 2] = t;
            }
            return maximum(array, n - 1);
        }
    }
    
//    ===================== Q6 ================
//    =================  multiply =============

    public static int product(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a == 1 || b == 1) {
            return ((b == 1) ? a : b);
        } else {
            if (b > 0) {
                return a + product(a, b - 1);
            } else {
                return -product(a, -b);
            }
        }
    }
    
//    ===================== Q7 ==================
//    ===================== bmm =================
     
    public static int bmm(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        if (a % b == 1 || b % a == 1) {
            return 1;
        }

        if (a % b == 0 || b % a == 0) {
            return min;
        } 
        
        else {
            int c = max % min;
            a = b;
            b = c;

            return bmm(a, b);
        }

    }
    
    
//    =================== Q8 ====================
//    =============== Truth Table ===============
    static int times = 1; 
    static int name = 97;
    public static void TruthTable(int n){
        if(n == 0){
            return;
        }
        System.out.print((char)(name) + " -> ");
        ++name;
         double num = Math.pow(2 , n);
         for(int i = 0 ; i < times ; ++i){
             for(int j = 0 ; j< num ; ++j){
                 if( j < num/2){
                     System.out.print("0 ");
                 }
                 else{
                     System.out.print("1 ");
                 }
             }
         }
         System.out.println();
         times *= 2;
         TruthTable(n-1);
         
    }
//    =================== Q9 ==========================
//    =============== the sum of factorials ===========
    
    public static int combo(int n) {
        if (n == 1) {
            return 1;
        } else {
            int temp = 1;
            for (int i = 2; i <= n; i++) {
                temp *= i;
            }

            return temp + combo(n - 1);
        }
    } 
    
//    ===================== Q10 =========================
//    =============== the sum of factorials 2 ===========
    
    public static double combo2(double n){
        if(n == 1.0){
            return 1;
        }
        
        else{
            double temp = 1.0;
            for(double i = 1.0 ; i <= n ; i++){
                temp *= i;
            }
            return (1.0/temp) + combo2( n - 1.0);
        }
        
    }
    
//    =================== Q11 =======================
//    =============== 2 - 5 - 10 rials ==============
    
    public static void distribute(int n  , String res){
         int coins [] = {2,5,10} ;
         if(n == 0){
             System.out.println(res);
             return;
         }
         // n < 0 is invalid so ... we'll skip it.
         if(n < 0 ){
             return;
         }
         else{
             for(int i = 0 ; i < 3 ; i++){
                 distribute(n-coins[i]  ,(res + coins[i] + " "));
             }
         }
    }
    
//    ====================== Q12 ================================ 
//    ======= 2 - 5 - 10 rials but a better version =============
    
         public static void distribute2(){
             
         }
    
//    =================== Q13 =====================
//    ================== hanoi ====================
    public static void hanoi(int n , char s , char d , char a){
 
    }
    
//    =================== Q16 =====================
//    =============== zir majmooee ================
    public static void SubSet(int [] array , int n){
    }
      
//    =================== Q20 =====================
//    =================== Ack =====================

    public static int Ack(int m , int n){
        if(n < 0 || m < 0){ 
            return 0; 
        }
        else if( n == 0 ) return n+1;
        else if( m == 0 ) return Ack(m-1,1);
        else return Ack(m-1 , Ack(m , n-1));
    }
        
    


}