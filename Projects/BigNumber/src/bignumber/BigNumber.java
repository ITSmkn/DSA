package bignumber;

import java.util.ArrayList;

public class BigNumber {
   ArrayList<Byte> Digits;
   int numberOfDigits;
   boolean ispositive = true;
   
   // printing the number
   public void print(){
       if(!ispositive){
           System.out.print("-");
       }
       for(int i = 0 ; i < (this.Digits).size() ; i++){
           System.out.print(this.Digits.get(i));
       }
       System.out.println();
   }
   
   public BigNumber(){
       Digits = new ArrayList<Byte>();
   }
   
   
   // converting int to BigNumber
   public BigNumber(int n){
       if(n < 0){
           this.ispositive = false;
           n = -n;
       }      
       String str = Integer.toString(n);
       int length = str.length();
       Digits = new ArrayList<Byte>();  
       int power = length;
       while(power != 0){
          byte temp = (byte) (((n%Math.pow(10, power)) - (n%Math.pow(10, power-1)))/(Math.pow(10, power-1)));          
          Digits.add(temp);
          power--;
       }
       this.numberOfDigits = this.Digits.size();
       
   }
   
   // converting string to BigNumber
   public BigNumber(String str){
        Digits = new ArrayList<Byte>();
        if((str.substring(0,1)).equals("-")){
            this.ispositive = false;
        }
        this.numberOfDigits = str.length();
        for(int i = 0 ; i < this.numberOfDigits ; i++){
            if(str.substring(i,i+1).equals("-")){
                continue;
            }
            int number = Integer.parseInt(str.substring(i,i+1));
            byte n = (byte) number; 
            (this.Digits).add(n);
        }
   }
   
   // converting an array of bytes to BigNumber
   public BigNumber(byte b[]){
       Digits = new ArrayList<Byte>();
       numberOfDigits = b.length ;
       for(int i = 0 ; i < numberOfDigits ; i++){
           Digits.add(b[i]);
       }
   }
   
   // Operators ...
   public BigNumber Plus(BigNumber bn){
       int max = Math.max(this.Digits.size(), bn.Digits.size()); 
       BigNumber bnew = new BigNumber();
       
       // filling the new array with 0!
       for (int i = 0; i < max + 1; i++){ 
           bnew.Digits.add((byte) 0);
       }
       
       byte carry = 0; 
       int maxCount = this.Digits.size() - 1; 
       int bnCount = bn.Digits.size() - 1 ;
       
       for (int i = max; i >= 0; i--) { 
            byte sum = carry; 
            if (maxCount >= 0){ 
                sum += this.Digits.get(maxCount); 
                maxCount--; 
            } 
            if (bnCount >= 0){ 
                sum += bn.Digits.get(bnCount); 
                bnCount--; 
            }
            // if the sum of two digits was greater than 9 then the carry variable will start from 1...
            carry = (byte) (sum / 10); 
            bnew.Digits.set(i, (byte) (sum % 10)); }
       
       
       if(bnew.Digits.get(0) == 0){ 
           bnew.Digits.remove(0); 
       } 
       
       return bnew;
 
   }
   
   public BigNumber fact(int n){
       ArrayList<
   }
}
