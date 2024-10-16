package s_1_solution;
 
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
    
    
//    =================== Q8 ==================
//    =============== Truth Table =============
    
    public static void TruthTable(int n){
        if( n == 0 ){
            System.out.println("input must be greater than 0!");
        }
        
        else{
            for(double i = 1 ; i <= Math.pow(2.0, n) ; i++){
                for(int j = n ; j >= 1 ; j--){
                    if( i <= (Math.pow(2.0,j)/2.0)){
                        System.out.print("0 ");
                    }
                    else{
                        System.out.print("1 ");
                    }
                }
                System.out.println();
            }
        }
    }
    
    
//    =================== Q11 ==================
//    =============== 2 - 5 - 10 rials ==============
    
    public static void distribute(int n){
        if(n < 2){
            System.out.println("input must be greater than 1!");            
        }
        
        else{
            
        }
    }
    
//    =================== Q12 ==================
//    =============== Truth Table ==============
    
}
