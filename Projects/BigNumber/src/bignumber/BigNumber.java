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
   
   // if (bn > this) ---> the result would be negative ...
   public int compareTo(BigNumber bn) { 
       
       if (this.Digits.size() != bn.Digits.size()) { 
            return this.Digits.size() - bn.Digits.size(); 
       } 
       
       for (int i = 0; i < this.Digits.size(); i++) { 
       if (this.Digits.get(i) != bn.Digits.get(i)) { 
           return this.Digits.get(i) - bn.Digits.get(i); 
       } 
   } 
   return 0; 
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
   
   public BigNumber(BigNumber b){
       this.Digits = b.Digits;
       this.ispositive = b.ispositive;
       this.numberOfDigits = b.numberOfDigits;
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
       
       // (-) + (-)  
       if(!this.ispositive && !bn.ispositive){
           bnew.ispositive = false;
       }
       
       // (-) + (+)
       if(!this.ispositive && bn.ispositive){
           this.ispositive = true;
           bnew = bn.Minus(this);  // (+) - (+)
           this.ispositive = false;
           
           return bnew;
       }
       
       // (+) + (-)
       if(this.ispositive && !bn.ispositive){
           bn.ispositive = true;
           bnew = this.Minus(bn);  // (+) - (+)
           bn.ispositive = false;
           
           return bnew;
       }
       
       
       // normal case ...
       
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
       bnew.numberOfDigits = bnew.Digits.size();
       return bnew;
 
   }
   
   
   public BigNumber Minus(BigNumber bn){
       BigNumber bnew = new BigNumber();
       
       // -1 - (+1) --> -2 
       if(!this.ispositive && bn.ispositive){
           this.ispositive = true;
           bnew = this.Plus(bn);
           this.ispositive = false;
           bnew.ispositive = false;
           
           return bnew;
       }
       
       // +1 - (-1) --> +2
       if(this.ispositive && !bn.ispositive){
           bn.ispositive = true;
           bnew = this.Plus(bn);
           bn.ispositive = false;
           return bnew;
       }
       
       // -1 - (-2) --> (+2) - (+1)
       if(!this.ispositive && !bn.ispositive){
           this.ispositive = true;
           bn.ispositive = true;
           bnew = bn.Minus(this);
           this.ispositive = false;
           bn.ispositive = false;
           
           return bnew;
       }
       
       // remaining cases ---> 1) (2 - 1) & (1 - 2)
       // one's result is positive and the other's is negative.
       
       // 1 - 2
       boolean resultIsPositive = true;
       if(this.compareTo(bn) < 0){
           resultIsPositive = false; 
           BigNumber temp = new BigNumber(this); 
           this.Digits = bn.Digits;
           this.ispositive = bn.ispositive;
           this.numberOfDigits = bn.numberOfDigits;
           bn = temp;
       }
       
       int max = this.Digits.size(); 
       for (int i = 0; i < max; i++){ 
           bnew.Digits.add((byte) 0);
       }
       
       byte borrow = 0; 
       int thisCount = this.Digits.size() - 1; 
       int bnCount = bn.Digits.size() - 1 ;
       
       
       for (int i = max - 1; i >= 0; i--) { 
           
           byte digit1 = (thisCount >= 0) ? this.Digits.get(thisCount) : 0;        
           byte digit2 = (bnCount >= 0) ? bn.Digits.get(bnCount) : 0; 
           
           digit1 -= borrow; 
           
           if (digit1 < digit2) { 
               digit1 += 10; borrow = 1; 
           } 
           
           else { 
               borrow = 0; 
           } 
           
           bnew.Digits.set(i, (byte) (digit1 - digit2)); 
           thisCount--; 
           bnCount--; 
       }
        while (bnew.Digits.size() > 1 && bnew.Digits.get(0) == 0) { 
            bnew.Digits.remove(0); 
        } 
        bnew.ispositive = resultIsPositive;
        
        return bnew;
   }
   
   
   public void shiftLeft(int positions) {  
       while(positions != 0){
           Digits.add((byte) 0); 
           positions--;
       }
   }
   
   
   public void shiftRight(int positions) { 
       int i = this.numberOfDigits-1;
       while(positions != 0){
           Digits.remove(i);
           i--;
           positions--;
       }
   }
   
}
