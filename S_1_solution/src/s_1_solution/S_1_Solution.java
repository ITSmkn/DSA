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
    
 public static void distribute2(int n, String res, int index) {
    if (n == 0) {
        System.out.println(res);
        return;
    }
    if (n < 0) {
        return;
    }

    int[] coins = {2, 5, 10};

    for (int i = index; i < coins.length; i++) {
        distribute2(n - coins[i], res + coins[i] + " ", i);
    }
}
       
         
    
//    =================== Q13 =====================
//    ================== hanoi ====================
    public static void hanoi1(int n , char s , char d , char a){
        if(n>0){
            hanoi1(n-1,s,a,d);
            System.out.println(s+"->"+d);
            hanoi1(n-1 , a , d,s);
        }
    }
    
    
//    =================== Q14 =====================
//    ================== hanoi 2 ====================
    public static void hanoi2(int n, char s, char a, char d){
    if (n == 1){
        System.out.println(s+"->"+a);
        System.out.println(a+"->"+d);
        return;
    }
    hanoi2(n - 1, s, a, d);
    System.out.println(s+"->"+a);
    hanoi2(n-1, d, a, s);
    System.out.println(a+"->"+d);
    hanoi2(n - 1, s, a, d);
}
    
    
        
//    =================== Q15 =====================
//    ================== queens ====================
    
    static void solveNQueens(int[] board, int row) {
        if (row == board.length) {
            printQueens(board);
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                // recursive 
                solveNQueens(board, row + 1);
                board[row] = -1;  
            }
        }
    }

    static boolean isSafe(int[] board, int row, int col) {
         
        for (int i = 0; i < row; i++) {
            if (board[i] == col) {
                return false;
            }
        }
 
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i] == j) {
                return false;
            }
        }

        
        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i] == j) {
                return false;
            }
        }

        return true;
    }

    static void printQueens(int[] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("x ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    
//    ==================== Q16 ======================
//    ================= Sub Sets ====================
    
     public static void printSubsets(ArrayList<Integer> set, int index, ArrayList<Integer> current) {
        if (index == set.size()) {
             
            System.out.println(current);
            return;
        }
    
        current.add(set.get(index));
        printSubsets(set, index + 1, current);

        current.remove(current.size() - 1);
        printSubsets(set, index + 1, current);
    }
     
//    ==================== Q17 ======================
//    ================= Soal 17  ====================    
     
     public static int S17(String s, int x) {
        s = s.trim();

        if (s.equals("x")) {
            return x;
        }

        int i = s.length() - 1;
        while (i >= 0) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') { 
                
                if (s.charAt(i) == '+') {
                    return S17(s.substring(0, i), x) + S17(s.substring(i + 1), x);
                } 
                
                else {
                    return S17(s.substring(0, i), x) - S17(s.substring(i + 1), x);
                }
            }
            i--;
        }

        i = s.length() - 1;
        while (i >= 0) {
            
            
            if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                
                if (s.charAt(i) == '*') {
                    return S17(s.substring(0, i), x) * S17(s.substring(i + 1), x);
                } 
                
                else {
                    return S17(s.substring(0, i), x) / S17(s.substring(i + 1), x);
                }
            }
            
            
            i--;
        }
        return 0;
    }
     
     
//    =================== Q18 - Q19 ===========================
//    =================== duplicate ===========================  
     
     public static void dup(String s){
         
         String temp = "";
         String rep = "";
         
         if (s.length() == 1 || s.isEmpty()){
             System.out.println(true);
             return;
         }
         for(int i = 1 ; i < s.length() ; i++){
         if (s.charAt(i) == s.charAt(0)){
             rep = s.substring(0,i);
             System.out.println(rep);
             
             break;
         }
         
         
     }
         for(int i = 0; i < s.length() ; i = i+rep.length()){
             temp = s.substring(i , i+rep.length());
             if(!temp.equals(rep)){
                 System.out.println(false);
                 return;
             }
             else if (temp.length() == 2 && temp.charAt(0) != temp.charAt(1)){
                 System.out.println(false);
                 return;
             }
             
             dup(temp.substring(1));
             
         
         }
     }
      
//    =================== Q20 =====================
//    =================== Ack =====================

    // the answer of this question has been sent to your pv!
        
    


}